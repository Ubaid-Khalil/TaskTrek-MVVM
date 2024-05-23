package com.ubaid.khalil.todo.mvvm.tasktrek.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ubaid.khalil.todo.mvvm.tasktrek.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1, exportSchema = true)
abstract class DatabaseTask : RoomDatabase() {

    abstract fun daoTask(): DaoTask

    class DatabaseTaskCallback @Inject constructor(
        private val databaseTask: Provider<DatabaseTask>,
        @ApplicationScope private val coroutineScope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val daoTask = databaseTask.get().daoTask()
            coroutineScope.launch {
                daoTask.apply {
                    addTask(Task(title = "Wash dishes"))
                    addTask(Task(title = "Do laundry"))
                    addTask(
                        Task(
                            title = "Buy groceries",
                            description = "Eggs, Oil, Vegetables and Flour, e.t.c"
                        )
                    )
                    addTask(Task(title = "Prepare food", isCompleted = true))
                    addTask(Task(title = "Visit zoo"))
                    addTask(Task(title = "Repair bike"))
                    addTask(Task(title = "Attend conference"))
                    addTask(Task(title = "Workout", isImportant = true))
                }
            }
        }
    }
}