package com.alberto.studycompanion.detail.todolist.domain.repository

import com.alberto.studycompanion.detail.todolist.domain.models.Task
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    suspend fun getAllTasks() : Flow<List<Task>>

    suspend fun addTask(task : Task)

    suspend fun getTaskById(id : String) : Task

    suspend fun deleteTask(id : String)
}