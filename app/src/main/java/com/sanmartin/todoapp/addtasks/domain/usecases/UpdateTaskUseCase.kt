package com.sanmartin.todoapp.addtasks.domain.usecases

import com.sanmartin.todoapp.addtasks.data.repositories.TaskRepository
import com.sanmartin.todoapp.addtasks.ui.model.TaskModel
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(taskModel: TaskModel) {
        taskRepository.updateTask(taskModel)
    }
}