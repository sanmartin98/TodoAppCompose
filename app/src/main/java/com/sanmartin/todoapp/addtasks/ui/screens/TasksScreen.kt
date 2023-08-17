package com.sanmartin.todoapp.addtasks.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sanmartin.todoapp.addtasks.ui.components.AddTasksDialog
import com.sanmartin.todoapp.addtasks.ui.components.CircularProgress
import com.sanmartin.todoapp.addtasks.ui.components.ErrorMessage
import com.sanmartin.todoapp.addtasks.ui.components.FabDialog
import com.sanmartin.todoapp.addtasks.ui.components.TasksList
import com.sanmartin.todoapp.addtasks.ui.viewmodels.TasksUiState
import com.sanmartin.todoapp.addtasks.ui.viewmodels.TasksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(tasksViewModel: TasksViewModel) {
    val showDialog by tasksViewModel.showDialog.observeAsState(initial = false)
    val uiState = tasksViewModel.uiState.collectAsState().value

    when(uiState) {
        is TasksUiState.Error -> { ErrorMessage() }
        TasksUiState.Loading -> { CircularProgress() }
        is TasksUiState.Success -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "TODO APP")},
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            titleContentColor = Color.White
                        )
                    )
                }
            ) {padding ->
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)) {
                    FabDialog(
                        modifier = Modifier.align(Alignment.BottomEnd)) { tasksViewModel.onShowDialog()
                    }
                    TasksList(
                        tasks = (uiState as TasksUiState.Success).tasks,
                        onTaskSelected = { tasksViewModel.onTaskSelected(it) },
                        onItemRemove = { tasksViewModel.onItemRemove(it) }
                    )
                    AddTasksDialog(
                        showDialog,
                        onDismiss = { tasksViewModel.onDialogClosed() },
                        onTaskAdded = { tasksViewModel.onTaskCreated(it) }
                    )
                }
            }
        }
    }
}

