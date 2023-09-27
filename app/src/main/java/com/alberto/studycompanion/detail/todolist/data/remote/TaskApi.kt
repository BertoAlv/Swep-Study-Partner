package com.alberto.studycompanion.detail.todolist.data.remote

import com.alberto.studycompanion.detail.todolist.data.remote.dto.TaskResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface TaskApi {

    companion object {
        const val BASE_URL = "https://swep-e038b-default-rtdb.firebaseio.com/"
    }

    @GET("tasks.json")
    suspend fun getTasks() : TaskResponse

    @PATCH("tasks.json")
    suspend fun insertTask(@Body task: TaskResponse)

    @DELETE("tasks/{id}.json")
    suspend fun deleteTaskById(@Path("id") taskId: String)

}