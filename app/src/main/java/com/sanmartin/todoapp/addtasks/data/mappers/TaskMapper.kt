package com.sanmartin.todoapp.addtasks.data.mappers

import com.sanmartin.todoapp.addtasks.data.entities.TaskEntity
import com.sanmartin.todoapp.addtasks.ui.model.TaskModel

fun TaskEntity.fromEntityToModel() = TaskModel(this.id, this.task, this.selected)

fun TaskModel.fromModelToEntity() = TaskEntity(this.id, this.task, this.selected)