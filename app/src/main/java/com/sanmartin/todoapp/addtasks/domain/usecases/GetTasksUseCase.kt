package com.sanmartin.todoapp.addtasks.domain.usecases

import com.sanmartin.todoapp.addtasks.data.repositories.TaskRepository
import com.sanmartin.todoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    operator fun invoke(): Flow<List<TaskModel>> = taskRepository.tasks

}