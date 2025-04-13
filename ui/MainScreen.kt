package com.example.myapplication.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.viewmodel.TaskViewModel




@Composable
fun MainScreen(taskViewModel: TaskViewModel = viewModel ()) {
    var newTask by remember { mutableStateOf("") }
    var selectedPriority by remember { mutableStateOf("Medium") }
    val taskList by taskViewModel.tasks.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        HomeDashboard()
        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            value = newTask,
            onValueChange = { newTask = it },
            label = { Text("New Task") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        PriorityDropdown(selectedPriority) {
            selectedPriority = it
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                taskViewModel.addTask(newTask, selectedPriority)
                newTask = ""
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = newTask.isNotBlank()
        ) {
            Text("Add Task")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(taskList) { task ->
                TaskItem(task = task, onDelete = { taskViewModel.deleteTask(it) })
            }
        }
    }
}

@Composable
fun PriorityDropdown(selectedPriority: String, onPrioritySelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val priorities = listOf("Low", "Medium", "High")

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Priority: $selectedPriority")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            priorities.forEach { priority ->
                DropdownMenuItem(
                    text = { Text(priority) },
                    onClick = {
                        onPrioritySelected(priority)
                        expanded = false
                    }
                )
            }
        }
    }
}
