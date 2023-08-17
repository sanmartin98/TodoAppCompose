package com.sanmartin.todoapp.addtasks.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanmartin.todoapp.addtasks.domain.usecases.AddTaskUseCase
import com.sanmartin.todoapp.addtasks.domain.usecases.DeleteTaskUseCase
import com.sanmartin.todoapp.addtasks.domain.usecases.GetTasksUseCase
import com.sanmartin.todoapp.addtasks.domain.usecases.UpdateTaskUseCase
import com.sanmartin.todoapp.addtasks.ui.viewmodels.TasksUiState.Success
import com.sanmartin.todoapp.addtasks.ui.viewmodels.TasksUiState.Error
import com.sanmartin.todoapp.addtasks.ui.viewmodels.TasksUiState.Loading
import com.sanmartin.todoapp.addtasks.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    getTasksUseCase: GetTasksUseCase
): ViewModel() {

    val uiState: StateFlow<TasksUiState> = getTasksUseCase().map(::Success)
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog = _showDialog


    fun onDialogClosed() {
        _showDialog.value = false
    }

    fun onTaskCreated(task: String) {
        _showDialog.value = false

        viewModelScope.launch {
            addTaskUseCase(TaskModel(task = task))
        }
    }

    fun onShowDialog() {
        _showDialog.value = true
    }

    fun onTaskSelected(task: TaskModel) {
        viewModelScope.launch {
            updateTaskUseCase(task.copy(selected = !task.selected))
        }
    }

    fun onItemRemove(task: TaskModel) {
        viewModelScope.launch {
            deleteTaskUseCase(task)
        }
    }
}