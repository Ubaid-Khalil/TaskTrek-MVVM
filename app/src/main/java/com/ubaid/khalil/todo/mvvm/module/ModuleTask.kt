package com.ubaid.khalil.todo.mvvm.module

import android.app.Application
import androidx.room.Room
import com.ubaid.khalil.todo.mvvm.data.DatabaseTask
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
object ModuleTask {
    @ApplicationScope
    @Provides
    @Singleton
    fun provideCoroutine() = CoroutineScope(SupervisorJob())

    @Provides
    @Singleton
    fun provideDatabaseTask(app: Application, callback: DatabaseTask.Callback) =
        Room.databaseBuilder(app, DatabaseTask::class.java, "task_database")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope