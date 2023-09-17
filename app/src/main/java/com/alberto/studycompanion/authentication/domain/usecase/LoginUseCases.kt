package com.alberto.studycompanion.authentication.domain.usecase

import javax.inject.Inject

data class LoginUseCases @Inject constructor(
    val loginWithEmailUseCase: LoginWithEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase,
    val validateEmailUseCase: ValidateEmailUseCase
)
