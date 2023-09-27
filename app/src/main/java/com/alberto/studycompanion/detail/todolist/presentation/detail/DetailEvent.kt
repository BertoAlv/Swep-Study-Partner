package com.alberto.studycompanion.detail.todolist.presentation.detail

sealed interface DetailEvent {

    data class NameChange(val name: String) : DetailEvent

    object TaskSave : DetailEvent
}