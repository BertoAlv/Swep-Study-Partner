package com.alberto.studycompanion.detail.todolist.domain.usecase

import com.alberto.studycompanion.detail.todolist.domain.models.Task
import com.alberto.studycompanion.detail.todolist.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(private val repository: ToDoRepository){

    suspend operator fun invoke() : Flow<List<Task>> {

        return repository.getAllTasks().distinctUntilChanged()

    }
}
