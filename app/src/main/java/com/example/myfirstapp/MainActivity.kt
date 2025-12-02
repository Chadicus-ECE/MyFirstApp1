package com.example.myfirstapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapp.ui.theme.MyFirstAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyFirstAppTheme {
                MainScreen(
                    onExplicitClick = {
                        // Explicit intent to SecondActivity
                        val intent = Intent(this, SecondActivity::class.java)
                        startActivity(intent)
                    },
                    onImplicitClick = {
                        // Implicit intent that matches SecondActivity's intent-filter
                        val intent = Intent("com.example.myfirstapp.OPEN_CHALLENGES")
                        startActivity(intent)
                    },
                    onViewImageClick = {
                        // Open ThirdActivity (camera / image view)
                        val intent = Intent(this, ThirdActivity::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

@Composable
fun MainScreen(
    onExplicitClick: () -> Unit,
    onImplicitClick: () -> Unit,
    onViewImageClick: () -> Unit
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Name and ID
            Text(
                text = "Full Name: Connor Nadgwick",
                fontSize = 22.sp
            )
            Text(
                text = "Student ID: 123456",   // <- replace with your real ID
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Start second activity explicitly
            Button(
                onClick = onExplicitClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Start Activity Explicitly")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Start second activity implicitly
            Button(
                onClick = onImplicitClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Start Activity Implicitly")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Go to image / camera activity
            Button(
                onClick = onViewImageClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("View Image Activity")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MyFirstAppTheme {
        MainScreen(
            onExplicitClick = {},
            onImplicitClick = {},
            onViewImageClick = {}
        )
    }
}
