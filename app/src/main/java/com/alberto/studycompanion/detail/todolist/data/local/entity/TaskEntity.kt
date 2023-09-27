package com.alberto.studycompanion.detail.todolist.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = false)
    val id : String,
    val name : String,
    val isDone : Boolean
)
