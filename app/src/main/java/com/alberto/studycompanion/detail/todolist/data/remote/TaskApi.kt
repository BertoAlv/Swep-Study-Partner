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

    @GET("users/{userId}/tasks.json")
    suspend fun getTasks(@Path("userId") userId: String) : TaskResponse

    @PATCH("users/{userId}/tasks.json")
    suspend fun insertTask(@Path("userId") userId: String, @Body task: TaskResponse)

    @DELETE("users/{userId}/tasks/{taskId}.json")
    suspend fun deleteTaskById(@Path("userId") userId: String,@Path("taskId") taskId: String)

}