package com.alberto.studycompanion.onboarding.data.repository

import android.content.SharedPreferences
import com.alberto.studycompanion.onboarding.domain.repository.OnboardingRepository

class OnboardingRepositoryImpl(private val sharedPreferences : SharedPreferences) : OnboardingRepository {

    companion object {
        private const val HAS_SEEN_ONBOARDING = "seenOnboarding"
    }

    override fun hasSeenOnboarding(): Boolean {
        return sharedPreferences.getBoolean(HAS_SEEN_ONBOARDING,false)
    }

    override fun completedOnboarding() {
        sharedPreferences.edit().putBoolean(HAS_SEEN_ONBOARDING,true).apply()
    }

}