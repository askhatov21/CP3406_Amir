package com.example.myapplication.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.com.example.myapplication.R



@Composable
fun WelcomeScreen(onGetStarted: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
    ) {
    Column(

        modifier = Modifier.align(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

            Text(
                "Study Smart!",
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFF163695)
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "Manage Your Daily Tasks",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Add a logo
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(280.dp)
                    .padding(bottom = 24.dp),

                )
            Spacer(modifier = Modifier.height(12.dp))


            Text(
                text = "Pain is temporary,\nGPA is forever.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray,
                lineHeight = 22.sp,
                textAlign = TextAlign.Center

            )
        }



        Button(
            onClick = onGetStarted,
            shape = RoundedCornerShape(40.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF163695), contentColor = Color.White

                ),

                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
                    .height(56.dp)
            ) {
                Text("Get Started", style = MaterialTheme.typography.titleMedium)
            }

        }
    }




