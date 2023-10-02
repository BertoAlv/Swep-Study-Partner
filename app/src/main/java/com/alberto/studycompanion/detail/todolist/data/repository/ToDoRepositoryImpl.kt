package com.alberto.studycompanion.detail.todolist.data.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.alberto.studycompanion.detail.todolist.data.local.TaskDAO
import com.alberto.studycompanion.detail.todolist.data.mapper.toDomain
import com.alberto.studycompanion.detail.todolist.data.mapper.toDto
import com.alberto.studycompanion.detail.todolist.data.mapper.toEntity
import com.alberto.studycompanion.detail.todolist.data.mapper.toSyncEntity
import com.alberto.studycompanion.detail.todolist.data.remote.TaskApi
import com.alberto.studycompanion.detail.todolist.data.remote.util.resultOf
import com.alberto.studycompanion.detail.todolist.data.sync.TaskSyncWorker
import com.alberto.studycompanion.detail.todolist.domain.models.Task
import com.alberto.studycompanion.detail.todolist.domain.repository.ToDoRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.time.Duration
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(
    private val taskApi: TaskApi,
    private val taskDAO: TaskDAO,
    private val workManager : WorkManager
    ) : ToDoRepository {

    override suspend fun addTask(task: Task) {
        taskDAO.insertTask(task.toEntity())
        resultOf {
            taskApi.insertTask(getCurrentUserId(),task.toDto())
        }.onFailure {
            taskDAO.insertTaskSync(task.toSyncEntity())
        }
    }

    override suspend fun getTaskById(id: String): Task {
        return taskDAO.getTaskById(id).toDomain()
    }

    override suspend fun deleteTask(id: String) {
        taskApi.deleteTaskById(userId = getCurrentUserId(), taskId = id)
        taskDAO.deleteTaskById(id)
    }

    override suspend fun getCurrentUserId(): String {
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        Log.d("COMPROBANTE","Clave usuario: ${currentUser?.uid}")
        return currentUser?.uid ?: ""
    }

    override suspend fun getAllTasks(): Flow<List<Task>> {
        val localFlow = taskDAO.getAllTasks(getCurrentUserId()).map {
            it.map { it.toDomain() }
        }

        val apiFlow = getTasksFromApi()

        return localFlow.combine(apiFlow) { db, _ ->
            db
        }
    }

    private suspend fun getTasksFromApi(): Flow<List<Task>> {
        return flow {
            resultOf {
                val tasks = taskApi.getTasks(getCurrentUserId()).toDomain(getCurrentUserId())
                insertTasks(tasks)
            }
            emit(emptyList<Task>())
        }.onStart {
            emit(emptyList())
        }
    }

    private suspend fun insertTasks(tasks: List<Task>) {
        tasks.forEach {
            taskDAO.insertTask(it.toEntity())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun syncTasks() {
        val worker = OneTimeWorkRequestBuilder<TaskSyncWorker>().setConstraints(
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        ).setBackoffCriteria(BackoffPolicy.EXPONENTIAL, Duration.ofMinutes(5))
            .build()

        workManager.beginUniqueWork("sync_task_id", ExistingWorkPolicy.REPLACE, worker).enqueue()
    }

}