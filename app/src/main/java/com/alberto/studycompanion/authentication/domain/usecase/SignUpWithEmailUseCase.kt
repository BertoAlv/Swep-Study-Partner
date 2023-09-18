package com.alberto.studycompanion.authentication.domain.usecase

import com.alberto.studycompanion.authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class SignUpWithEmailUseCase @Inject constructor(val repository: AuthenticationRepository) {

    suspend operator fun invoke(email : String, password : String) : Result<Unit> {
        return repository.signUp(email, password)
    }

}