package com.sanmartin.todoapp.addtasks.data.repositories

import com.sanmartin.todoapp.addtasks.data.dao.TaskDao
import com.sanmartin.todoapp.addtasks.data.entities.TaskEntity
import com.sanmartin.todoapp.addtasks.data.mappers.fromEntityToModel
import com.sanmartin.todoapp.addtasks.data.mappers.fromModelToEntity
import com.sanmartin.todoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor (
    private val taskDao: TaskDao
) {

    val tasks: Flow<List<TaskModel>> = taskDao.getTasks().map { items ->
        items.map { it.fromEntityToModel() }
    }

    suspend fun addTask(taskModel: TaskModel) {
        taskDao.addTask(taskModel.fromModelToEntity())
    }

    suspend fun updateTask(taskModel: TaskModel) {
        taskDao.updateTask(taskModel.fromModelToEntity())
    }

    suspend fun deleteTask(taskModel: TaskModel) {
        taskDao.deleteTask(taskModel.fromModelToEntity())
    }
}