package com.alberto.studycompanion.home.di

import com.alberto.studycompanion.authentication.data.repository.AuthenticationRepositoryImpl
import com.alberto.studycompanion.authentication.domain.repository.AuthenticationRepository
import com.alberto.studycompanion.home.data.repository.HomeRepositoryImpl
import com.alberto.studycompanion.home.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeRepository() : HomeRepository {
        return HomeRepositoryImpl()
    }

}