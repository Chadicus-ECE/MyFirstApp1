package com.example.myfirstapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapp.ui.theme.MyFirstAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFirstAppTheme {
                MainScreen(onExplicitClick = {
                    val intent = Intent(this, SecondActivity::class.java)
                    startActivity(intent)
                }, onImplicitClick = {
                    val intent = Intent("com.example.myfirstapp.OPEN_CHALLENGES")
                    startActivity(intent)
                })
            }
        }
    }
}

@Composable
fun MainScreen(onExplicitClick: () -> Unit, onImplicitClick: () -> Unit) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Full Name: Connor Nadgwick", fontSize = 22.sp)
            Text(text = "Student ID: 1367169", fontSize = 20.sp, modifier = Modifier.padding(top = 8.dp))

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = onExplicitClick, modifier = Modifier.fillMaxWidth()) {
                Text("Start Activity Explicitly")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onImplicitClick, modifier = Modifier.fillMaxWidth()) {
                Text("Start Activity Implicitly")
            }
        }
    }
}
