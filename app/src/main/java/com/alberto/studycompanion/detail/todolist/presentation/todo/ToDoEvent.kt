package com.alberto.studycompanion.detail.todolist.presentation.todo

import com.alberto.studycompanion.detail.todolist.domain.models.Task

sealed interface ToDoEvent {

    data class completeTask(val task: Task) : ToDoEvent

    data class deleteTask(val id : String) : ToDoEvent

}