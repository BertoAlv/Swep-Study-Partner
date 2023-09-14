package com.alberto.studycompanion.onboarding.domain.usecase

import com.alberto.studycompanion.onboarding.domain.repository.OnboardingRepository
import javax.inject.Inject

class HasCompletedOnboardingUseCase @Inject constructor(private val repository: OnboardingRepository) {

    operator fun invoke() = repository.completedOnboarding()
}