package com.alberto.studycompanion.detail.todolist.domain.usecase

import com.alberto.studycompanion.detail.todolist.domain.models.Task
import com.alberto.studycompanion.detail.todolist.domain.repository.ToDoRepository

import javax.inject.Inject

class CompleteTaskUseCase @Inject constructor(private val repository: ToDoRepository) {

    suspend operator fun invoke(task : Task){
        val newTask = if(task.isDone) {
            task.copy(
                isDone = false
            )
        }else{
            task.copy(
                isDone = true
            )
        }
        repository.addTask(newTask)
    }

}