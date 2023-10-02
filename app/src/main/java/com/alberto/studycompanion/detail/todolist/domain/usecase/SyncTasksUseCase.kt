package com.alberto.studycompanion.detail.todolist.domain.usecase

import com.alberto.studycompanion.detail.todolist.domain.repository.ToDoRepository
import javax.inject.Inject

class SyncTasksUseCase @Inject constructor(private val repository: ToDoRepository) {

    suspend operator fun invoke(){
        repository.syncTasks()
    }
}