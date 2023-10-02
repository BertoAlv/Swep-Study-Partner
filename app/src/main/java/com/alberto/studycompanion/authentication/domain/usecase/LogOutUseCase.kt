package com.alberto.studycompanion.authentication.domain.usecase

import com.alberto.studycompanion.authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(private val repository: AuthenticationRepository){

    suspend operator fun invoke() {
        return repository.logout()
    }
}