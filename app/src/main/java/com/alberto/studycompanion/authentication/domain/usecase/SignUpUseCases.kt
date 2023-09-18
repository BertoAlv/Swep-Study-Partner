package com.alberto.studycompanion.authentication.domain.usecase

import javax.inject.Inject

data class SignUpUseCases @Inject constructor(
    val signUpWithEmailUseCase: SignUpWithEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase,
    val validateEmailUseCase: ValidateEmailUseCase
)
