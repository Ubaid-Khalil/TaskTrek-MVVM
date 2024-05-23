package com.ubaid.khalil.todo.mvvm.tasktrek.di

import android.app.Application
import androidx.room.Room
import com.ubaid.khalil.todo.mvvm.tasktrek.data.DatabaseTask
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleTask {
    @Singleton
    @Provides
    fun provideDatabaseTask(application: Application, callback: DatabaseTask.DatabaseTaskCallback) =
        Room.databaseBuilder(application, DatabaseTask::class.java, "task_db")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()

    @Singleton
    @Provides
    fun provideTaskDao(databaseTask: DatabaseTask) = databaseTask.daoTask()

    @ApplicationScope
    @Singleton
    @Provides
    fun provideCoroutineScope() = CoroutineScope(SupervisorJob())
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope