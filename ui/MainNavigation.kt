package com.example.myapplication.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.myapplication.viewmodel.TaskViewModel
import java.com.example.myapplication.R
import java.time.LocalDate

@Composable
fun MainNavigation(viewModel: TaskViewModel) {
    var selectedScreen by remember { mutableStateOf("Home") }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedScreen == "Home",
                    onClick = { selectedScreen = "Home" },
                    icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                    label = { Text("Home")}
                )
                NavigationBarItem(
                    selected = selectedScreen == "Today",
                    onClick = { selectedScreen = "Today" },
                    icon = { Icon(Icons.Filled.Today, contentDescription = null) },
                    label = { Text("Today")}
                )
                NavigationBarItem(
                    selected = selectedScreen == "Focus",
                    onClick = { selectedScreen = "Focus" },
                    icon = { Icon(Icons.Filled.Timer, contentDescription = null) },
                    label = { Text("Focus") }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            when (selectedScreen) {
                "Home" -> HomeDashboard()
                "Today" -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    TodayScreen()
                }

                "Focus" -> FocusTimerScreen()
            }
        }
    }
}




@Composable
fun LoginScreen(
    onLoginClicked: () -> Unit,
    onForgotPassword: () -> Unit,
    onSignUp: () -> Unit
) {
    var email by remember { mutableStateOf("")}
    var password by remember { mutableStateOf("")}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Study Smart!",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Placeholder image
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(140.dp)
                .padding(bottom = 16.dp)
        )

        Text("Let's get to it!", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(8.dp))


        Spacer(modifier = Modifier.height(24.dp))


        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onLoginClicked,
            shape = RoundedCornerShape(32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF163695),
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Sign in")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = onForgotPassword) {
            Text("Forget Password?", color = Color(0xFF163695))
        }

        Spacer(modifier = Modifier.height(32.dp))
        TextButton(onClick = onSignUp) {
            Text("Don't have an account? Sign up here", color = Color(0xFF163695))
        }
    }

}
@Composable
fun HomeDashboard() {
    var taskList by remember {
        mutableStateOf(
            listOf(
                "Math Homework" to 0.4f,
                "Science Report" to 0.22f,
                "Programming Practical" to  0.6f
            )
        )
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
              onClick = {
                  taskList = taskList + ("New Task" to 0f)
              },
                containerColor = Color(0XFF163695)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Task", tint = Color.White)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Hello, Boss!", style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.height(4.dp))

            Text("You've got ${taskList.size} tasks today", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(4.dp))



            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Search something.. ") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                modifier = Modifier.fillMaxWidth()

            )
            Spacer(modifier = Modifier.height(24.dp))


            Text("My tasks", style = MaterialTheme.typography.titleMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                listOf("Recently", "Today", "Upcoming", "Later").forEach {
                    Text(it, style = MaterialTheme.typography.labelMedium)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // TASK GRID
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(taskList) { (title, progress) ->
                    TaskCard(title = title, progress = progress)
                }
            }
        }
    }
}

@Composable
fun TaskCard(title: String, progress: Float) {
    Card(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(130.dp),
         colors = CardDefaults.cardColors(containerColor = Color(0xFFEAE7F5)),
         shape = RoundedCornerShape(16.dp)
    ){
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { progress },
                color = Color(0XFF163695),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text("Progress ${(progress * 100).toInt()}%", style = MaterialTheme.typography.labelSmall)
        }
    }

}

@Composable
fun SearchBar() {
    var searchText by remember { mutableStateOf("") }

    OutlinedTextField(
        value = searchText,
        onValueChange = { searchText = it},
        label = { Text("Search something...") },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Search Icon")
        },
        modifier = Modifier.fillMaxWidth()
    )
}



@RequiresApi(Build.VERSION_CODES.O)

@Composable
fun TodayScreen() {
    val tasks = remember { mutableStateListOf("Math", "Science", "Programming") }
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)

    ) {
        Text("Today", style = MaterialTheme.typography.headlineSmall)
        Text(LocalDate.now().toString(), style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("April 12, 2025")

        Spacer(modifier = Modifier.height(8.dp))


        Button(onClick = { tasks.add("New Task") },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF163695),
                contentColor = Color.White
            )

        ) {
            Text("+ Add a task")
        }
        Spacer(modifier = Modifier.height(16.dp))
        tasks.forEachIndexed { index, task ->
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE8E5F1)),
                    shape = RoundedCornerShape(16.dp)

                ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text(task, style = MaterialTheme.typography.titleMedium)
                        Text("Task description", style = MaterialTheme.typography.bodySmall)
                    }
                    IconButton(onClick = { tasks.removeAt(index) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }
        }

    }
}

@Composable
fun StatsScreen() {
    listOf(0.1f, 0.3f, 0.6f, 0.8f, 0.7)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Title
        Text("Your Stats", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(4.dp))

        // Subheading
        Text("+750 tasks complete", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(24.dp))

        // Chart Placeholder
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.LightGray, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("[Chart Placeholder]", color = Color.DarkGray)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Task counts row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Complete Tasks: 750", style = MaterialTheme.typography.bodyMedium)
            Text("Uncomplete Tasks: 96", style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Rank
        Text("User Rank: Bronze", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(24.dp))

        // History Cards
        listOf(
            "2023 - 920 Tasks Complete",
            "2025 - 30 Tasks Complete"
        ).forEach { stat ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE8E5F1)  // soft purple/gray background
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp)) {
                    Text(stat, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
