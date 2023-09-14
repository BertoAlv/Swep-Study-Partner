package com.alberto.studycompanion.onboarding.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.alberto.studycompanion.onboarding.domain.usecase.HasCompletedOnboardingUseCase
import com.alberto.studycompanion.onboarding.domain.usecase.HasSeenOnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val hasCompletedOnboardingUseCase: HasCompletedOnboardingUseCase,
    private val hasSeenOnboardingUseCase: HasSeenOnboardingUseCase,
) : ViewModel() {

    var hasSeenOnboarding by mutableStateOf(hasSeenOnboardingUseCase())
        private set

    fun completeOnboarding() {
        hasCompletedOnboardingUseCase()
        hasSeenOnboarding = true
    }
}