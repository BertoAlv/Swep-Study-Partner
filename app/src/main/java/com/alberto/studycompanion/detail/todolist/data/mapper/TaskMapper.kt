package com.alberto.studycompanion.detail.todolist.data.mapper

import com.alberto.studycompanion.detail.todolist.data.local.entity.TaskEntity
import com.alberto.studycompanion.detail.todolist.data.remote.dto.TaskDto
import com.alberto.studycompanion.detail.todolist.data.remote.dto.TaskResponse
import com.alberto.studycompanion.detail.todolist.domain.models.Task


fun TaskEntity.toDomain(): Task {
    return Task(
        id = this.id,
        name = this.name,
        isDone = this.isDone
    )
}

fun Task.toEntity(): TaskEntity {
    return TaskEntity(
        id = this.id,
        name = this.name,
        isDone = this.isDone
    )
}

fun TaskResponse.toDomain(): List<Task> {
    return this.entries.map {
        val id = it.key
        val dto = it.value
        Task(
            id = id,
            name = dto.name,
            isDone = dto.isDone
        )
    }
}

fun Task.toDto(): TaskResponse {
    val dto = TaskDto(
        name = this.name,
        isDone = this.isDone
    )
    return mapOf(id to dto)
}

