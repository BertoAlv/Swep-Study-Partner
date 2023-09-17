package com.alberto.studycompanion.authentication.domain.usecase

import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor() {

    operator fun invoke(password : String) : PasswordResult {
        if (password.length < 8){
            return PasswordResult.Invalid("Password must have at least 8 characters")
        }
        if (!password.any { it.isLowerCase() }) {
            return PasswordResult.Invalid("Password must have at least 1 lowercase character")
        }

        if (!password.any { it.isUpperCase() }) {
            return PasswordResult.Invalid("Password must have at least 1 uppercase character")
        }

        if (!password.any { it.isDigit() }) {
            return PasswordResult.Invalid("Password must have at least 1 digit")
        }

        return PasswordResult.Valid
    }

}

sealed class PasswordResult {
    object Valid : PasswordResult()
    data class Invalid(val errorMessage : String) : PasswordResult()
}