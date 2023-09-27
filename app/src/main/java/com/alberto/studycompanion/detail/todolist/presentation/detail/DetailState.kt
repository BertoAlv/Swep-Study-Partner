package com.alberto.studycompanion.detail.todolist.presentation.detail

data class DetailState(
    val id: String? = null,
    val taskName: String = "",
    val isSaved : Boolean = false
)
