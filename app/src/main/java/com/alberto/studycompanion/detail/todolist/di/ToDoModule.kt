package com.alberto.studycompanion.detail.todolist.di

import android.content.Context
import androidx.room.Room
import com.alberto.studycompanion.detail.todolist.data.local.TaskDAO
import com.alberto.studycompanion.detail.todolist.data.local.TaskDatabase
import com.alberto.studycompanion.detail.todolist.data.remote.TaskApi
import com.alberto.studycompanion.detail.todolist.data.repository.ToDoRepositoryImpl
import com.alberto.studycompanion.detail.todolist.domain.repository.ToDoRepository
import com.squareup.moshi.Moshi

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ToDoModule {

    @Provides
    @Singleton
    fun provideToDoRepository(api : TaskApi, dao : TaskDAO) : ToDoRepository {
        return ToDoRepositoryImpl(api,dao)
    }

    @Provides
    @Singleton
    fun provideTaskDAO(@ApplicationContext context: Context) : TaskDAO {
        return Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            "tasks_db"
        ).build().dao
    }

    @Provides
    @Singleton
    fun providesMoshi() : Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).build()
    }

    @Singleton
    @Provides
    fun provideTaskApi(client: OkHttpClient): TaskApi {
        return Retrofit.Builder().baseUrl(TaskApi.BASE_URL).client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(TaskApi::class.java)
    }

}