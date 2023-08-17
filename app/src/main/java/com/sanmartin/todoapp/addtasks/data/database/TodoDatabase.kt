package com.sanmartin.todoapp.addtasks.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sanmartin.todoapp.addtasks.data.dao.TaskDao
import com.sanmartin.todoapp.addtasks.data.entities.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}