package com.alberto.studycompanion.detail.todolist.data.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.alberto.studycompanion.detail.todolist.data.local.TaskDAO
import com.alberto.studycompanion.detail.todolist.data.local.entity.TaskSyncEntity
import com.alberto.studycompanion.detail.todolist.data.mapper.toDomain
import com.alberto.studycompanion.detail.todolist.data.mapper.toDto
import com.alberto.studycompanion.detail.todolist.data.remote.TaskApi
import com.alberto.studycompanion.detail.todolist.data.remote.util.resultOf
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

@HiltWorker
class TaskSyncWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted val workerParameters: WorkerParameters,
    private val api: TaskApi,
    private val dao: TaskDAO
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        if (runAttemptCount >= 3) {
            return Result.failure()
        }

        val items = dao.getAllTasksSync()

        return try {
            supervisorScope {
                val jobs = items.map { items -> launch { sync(items) } }
                jobs.forEach { it.join() }
            }
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    private suspend fun sync(entity: TaskSyncEntity) {
        val task = dao.getTaskById(entity.id).toDomain()
        val taskDto = dao.getTaskById(entity.id).toDomain().toDto()
        resultOf {
            api.insertTask(task.userId,taskDto)
        }.onSuccess {
            dao.deleteTaskSync(entity)
        }.onFailure {
            throw it
        }
    }
}