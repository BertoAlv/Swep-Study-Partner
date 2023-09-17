package com.alberto.studycompanion.authentication.domain.usecase

import com.alberto.studycompanion.authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class LoginWithEmailUseCase @Inject constructor(private val repositoryImpl: AuthenticationRepository) {

    suspend operator fun invoke(email : String, password: String) : Result<Unit> {
        return repositoryImpl.login(email, password)
    }

}