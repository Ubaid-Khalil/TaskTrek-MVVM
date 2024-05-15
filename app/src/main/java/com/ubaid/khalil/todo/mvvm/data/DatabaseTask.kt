package com.ubaid.khalil.todo.mvvm.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ubaid.khalil.todo.mvvm.module.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1, exportSchema = true)
abstract class DatabaseTask : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "task_database"
    }

    abstract fun daoTask(): DaoTask

    class Callback @Inject constructor(
        private val database: Provider<DatabaseTask>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val daoTask = database.get().daoTask()

            applicationScope.launch {
                daoTask.addTask(Task(title = "Wash dishes"))
                daoTask.addTask(Task(title = "Do laundry"))
                daoTask.addTask(
                    Task(
                        title = "Buy groceries",
                        description = "Eggs, Oil, Vegetables and Flour, e.t.c"
                    )
                )
                daoTask.addTask(Task(title = "Prepare food", isCompleted = 1))
                daoTask.addTask(Task(title = "Visit zoo"))
                daoTask.addTask(Task(title = "Repair bike"))
                daoTask.addTask(Task(title = "Attend conference"))
                daoTask.addTask(Task(title = "Workout", isImportant = 1))
            }
        }
    }
}