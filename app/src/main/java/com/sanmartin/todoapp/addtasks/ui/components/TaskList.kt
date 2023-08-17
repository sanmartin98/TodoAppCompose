package com.sanmartin.todoapp.addtasks.ui.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.sanmartin.todoapp.addtasks.ui.model.TaskModel

@Composable
fun TasksList(tasks: List<TaskModel>, onTaskSelected: (TaskModel) -> Unit, onItemRemove: (TaskModel) -> Unit) {
    LazyColumn {
        items(tasks, key = { it.id }) {
            ItemTask(task = it, onTaskSelected = onTaskSelected, onItemRemove)
        }
    }
}

@Composable
fun ItemTask(task: TaskModel, onTaskSelected: (TaskModel) -> Unit, onItemRemove: (TaskModel) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = {
                    onItemRemove(task)
                })
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = task.task,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            )
            Checkbox(
                checked = task.selected,
                onCheckedChange = { onTaskSelected(task) })
        }
    }
}