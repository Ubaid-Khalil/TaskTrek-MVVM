package com.ubaid.khalil.todo.mvvm.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.text.DateFormat

@Entity(tableName = "tasks_table")
@Parcelize
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    @ColumnInfo(name = "is_important")
    val isImportant: Boolean = false,
    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean = false,
    val created: Long = System.currentTimeMillis()
) : Parcelable {
    val dateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(created)
}