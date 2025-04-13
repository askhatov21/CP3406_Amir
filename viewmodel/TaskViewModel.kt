package com.example.myapplication.viewmodel


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import model.Task
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class TaskViewModel: ViewModel() {

    private val _tasks =  MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()
    private var taskId = 0



    fun addTask(title: String, priority: String) {
        if (title.isNotBlank()) {
            val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date())
            val newTask = Task(
                id = ++taskId,
                title = title,
                priority = priority,
                timestamp = timestamp
            )
            _tasks.value = _tasks.value + newTask
            }

        }

    fun deleteTask(task: Task) {
        _tasks.value = _tasks.value - task
        }
    }
