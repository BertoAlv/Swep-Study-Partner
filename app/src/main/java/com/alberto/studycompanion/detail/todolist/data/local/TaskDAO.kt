package com.alberto.studycompanion.detail.todolist.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alberto.studycompanion.detail.todolist.data.local.entity.TaskEntity
import com.alberto.studycompanion.detail.todolist.data.local.entity.TaskSyncEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(tasks : List<TaskEntity>)

    @Query("SELECT * FROM TaskEntity WHERE id = :id")
    fun getTaskById(id : String) : TaskEntity

    @Query("SELECT * FROM TaskEntity WHERE userId = :userId")
    fun getAllTasks(userId : String) : Flow<List<TaskEntity>>

    @Query("DELETE FROM TaskEntity WHERE id = :id")
    suspend fun deleteTaskById(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskSync(taskSyncEntity: TaskSyncEntity)

    @Query("SELECT * FROM TaskSyncEntity")
    fun getAllTasksSync(): List<TaskSyncEntity>

    @Delete
    suspend fun deleteTaskSync(taskSyncEntity: TaskSyncEntity)

}