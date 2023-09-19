package com.alberto.studycompanion.home.presentation

import com.alberto.studycompanion.home.domain.models.Method

data class HomeState(
    val methods : List<Method> = emptyList()
)