package com.alberto.studycompanion.authentication.di

import com.alberto.studycompanion.authentication.data.matcher.EmailMatcherImpl
import com.alberto.studycompanion.authentication.data.repository.AuthenticationRepositoryImpl
import com.alberto.studycompanion.authentication.domain.matcher.EmailMatcher
import com.alberto.studycompanion.authentication.domain.repository.AuthenticationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {

    @Provides
    @Singleton
    fun provideAuthenticationRepository() : AuthenticationRepository {
        return AuthenticationRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideEmailMatcher(): EmailMatcher {
        return EmailMatcherImpl()
    }

}