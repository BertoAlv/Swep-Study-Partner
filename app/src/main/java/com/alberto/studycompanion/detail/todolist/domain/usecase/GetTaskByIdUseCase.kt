package com.alberto.studycompanion.detail.todolist.domain.usecase

import com.alberto.studycompanion.detail.todolist.domain.models.Task
import com.alberto.studycompanion.detail.todolist.domain.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTaskByIdUseCase @Inject constructor(private val repository: ToDoRepository) {

    suspend operator fun invoke(id : String) : Task {
        return withContext(Dispatchers.IO) {
            repository.getTaskById(id)
        }
    }

}