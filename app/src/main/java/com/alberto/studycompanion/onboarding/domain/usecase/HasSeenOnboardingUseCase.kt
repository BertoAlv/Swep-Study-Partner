package com.alberto.studycompanion.onboarding.domain.usecase

import com.alberto.studycompanion.onboarding.domain.repository.OnboardingRepository
import javax.inject.Inject

class HasSeenOnboardingUseCase @Inject constructor(private val repository: OnboardingRepository) {

    operator fun invoke() : Boolean = repository.hasSeenOnboarding()
}