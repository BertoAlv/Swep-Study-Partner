package com.alberto.studycompanion.detail.todolist.domain.usecase

import com.alberto.studycompanion.detail.todolist.domain.models.Task
import com.alberto.studycompanion.detail.todolist.domain.repository.ToDoRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(private val repository: ToDoRepository){

    suspend operator fun invoke(id : String){
        repository.deleteTask(id)
    }

}