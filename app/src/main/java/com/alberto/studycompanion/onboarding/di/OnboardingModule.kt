package com.alberto.studycompanion.onboarding.di

import android.content.Context
import android.content.SharedPreferences
import com.alberto.studycompanion.onboarding.data.repository.OnboardingRepositoryImpl
import com.alberto.studycompanion.onboarding.domain.repository.OnboardingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object OnboardingModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("habits_onboarding_preferences", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideOnboardingRepository(sharedPreferences: SharedPreferences) : OnboardingRepository {
        return OnboardingRepositoryImpl(sharedPreferences)
    }

}