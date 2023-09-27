package com.alberto.studycompanion.detail.todolist.data.repository

import com.alberto.studycompanion.detail.todolist.data.local.TaskDAO
import com.alberto.studycompanion.detail.todolist.data.mapper.toDomain
import com.alberto.studycompanion.detail.todolist.data.mapper.toDto
import com.alberto.studycompanion.detail.todolist.data.mapper.toEntity
import com.alberto.studycompanion.detail.todolist.data.remote.TaskApi
import com.alberto.studycompanion.detail.todolist.data.remote.util.resultOf
import com.alberto.studycompanion.detail.todolist.domain.models.Task
import com.alberto.studycompanion.detail.todolist.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.time.ZonedDateTime
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(private val taskApi: TaskApi, private val taskDAO: TaskDAO) : ToDoRepository {

    override suspend fun addTask(task: Task) {
        taskApi.insertTask(task.toDto())
    }

    override suspend fun getTaskById(id: String): Task {
        return taskDAO.getTaskById(id).toDomain()
    }

    override suspend fun deleteTask(id: String) {
        taskApi.deleteTaskById(id)
        taskDAO.deleteTaskById(id)
    }

    override suspend fun getAllTasks(): Flow<List<Task>> {
        val localFlow = taskDAO.getAllTasks().map {
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
                val tasks = taskApi.getTasks().toDomain()
                insertTasks(tasks)
            }
            emit(emptyList<Task>())
        }.onStart {
            emit(emptyList())
        }
    }

    private suspend fun insertTasks(tasks: List<Task>) {
        taskDAO.insertTasks(tasks.map { it.toEntity() })
    }

}