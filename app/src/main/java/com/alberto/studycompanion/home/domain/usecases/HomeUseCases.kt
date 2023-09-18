package com.alberto.studycompanion.home.domain.usecases

import javax.inject.Inject

data class HomeUseCases @Inject constructor(
    val getMethodsUseCase: GetMethodsUseCase
)
