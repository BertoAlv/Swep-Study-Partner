package com.alberto.studycompanion.detail.todolist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alberto.studycompanion.detail.todolist.data.local.entity.TaskEntity


@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDatabase() : RoomDatabase() {

    abstract val dao : TaskDAO

}