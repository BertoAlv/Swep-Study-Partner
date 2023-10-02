package com.alberto.studycompanion.detail.todolist.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskSyncEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String
)