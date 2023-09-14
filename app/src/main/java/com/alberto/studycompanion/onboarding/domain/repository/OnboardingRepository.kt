package com.alberto.studycompanion.onboarding.domain.repository

interface OnboardingRepository {

    fun hasSeenOnboarding() : Boolean

    fun completedOnboarding()

}