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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapp.ui.theme.MyFirstAppTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyFirstAppTheme {
                SecondScreen(
                    onMainClick = {
                        // Launch MainActivity (avoid stacking)
                        val intent = Intent(this, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

@Composable
fun SecondScreen(onMainClick: () -> Unit) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.Start
        ) {

            // Title
            Text(
                text = "Mobile Software Engineering Challenges:",
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // List of challenges (use any 5+)
            val challenges = listOf(
                "1. Handling device fragmentation",
                "2. Managing battery and performance constraints",
                "3. Securing data storage and network communication",
                "4. Supporting offline mode and data sync",
                "5. Ensuring seamless UI/UX across devices",
                "6. Handling slow and intermittent networks",
                "7. Managing background work efficiently"
            )

            for (item in challenges) {
                Text(
                    text = item,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Return to MainActivity
            Button(
                onClick = onMainClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Main Activity")
            }
        }
    }
}
