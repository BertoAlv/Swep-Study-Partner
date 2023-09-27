package com.alberto.studycompanion.detail.todolist.presentation.todo

import com.alberto.studycompanion.detail.todolist.domain.models.Task

data class ToDoState(
    val tasks : List<Task> = emptyList()
)
