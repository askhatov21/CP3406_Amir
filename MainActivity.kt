package com.example.studyandmanagement
package com.example.amirproject //

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyTimeTheme {
                val viewModel: TaskViewModel = viewModel()
                MainScreen(viewModel)
            }
        }
    }
}

@Composable
fun StudyTimeTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(),
        typography = Typography(),
        content = content
    )
}

class TaskViewModel : ViewModel() {
    var taskList by mutableStateOf(listOf("Math Homework", "Read 10 pages", "Finish Project"))
        private set

    var newTask by mutableStateOf("")
        private set

    var studyGoals by mutableStateOf("Complete 3 assignments this week")
        private set

    var progressTracker by mutableStateOf("50% of weekly goals achieved")
        private set

    fun addTask(task: String) {
        if (task.isNotBlank()) {
            taskList = taskList + task
        }
    }

    fun updateNewTask(task: String) {
        newTask = task
    }
}

@Composable
fun MainScreen(viewModel: TaskViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Study & Time Management", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = viewModel.newTask,
            onValueChange = { viewModel.updateNewTask(it) },
            label = { Text("New Task") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            viewModel.addTask(viewModel.newTask)
            viewModel.updateNewTask("")
        }) {
            Text("Add Task")
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text("Tasks:", style = MaterialTheme.typography.bodyLarge)
        viewModel.taskList.forEach { task ->
            TaskItem(task)
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text("Study Goals:", style = MaterialTheme.typography.bodyLarge)
        Text(viewModel.studyGoals, modifier = Modifier.padding(4.dp))

        Spacer(modifier = Modifier.height(10.dp))

        Text("Progress Tracker:", style = MaterialTheme.typography.bodyLarge)
        Text(viewModel.progressTracker, modifier = Modifier.padding(4.dp))
    }
}

@Composable
fun TaskItem(task: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Text(
            text = task,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    val fakeViewModel = TaskViewModel().apply {
        updateNewTask("Sample Task")
    }
    StudyTimeTheme {
        MainScreen(fakeViewModel)
    }
}