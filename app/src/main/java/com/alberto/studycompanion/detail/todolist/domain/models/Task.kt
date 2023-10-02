package com.alberto.studycompanion.detail.todolist.domain.models

data class Task(
    val id : String,
    val name : String,
    val isDone : Boolean,
    val userId : String
)
