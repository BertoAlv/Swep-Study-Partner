package com.alberto.studycompanion.authentication.domain.repository

interface AuthenticationRepository {

    suspend fun login(email : String, password : String) : Result<Unit>

    suspend fun signUp(email : String, password : String) : Result<Unit>

    fun getUserId() : String?
}