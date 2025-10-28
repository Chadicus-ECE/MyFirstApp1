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

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFirstAppTheme {
                SecondScreen(onMainClick = {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                })
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Mobile Software Engineering Challenges:",
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            val challenges = listOf(
                "1. Managing software updates for constantly changing hardware",
                "2. Making optimized programs to run using minimal hardware",
                "3. Optimizing battery and memory usage",
                "4. Handling multiple screen sizes",
                "5. Making sure any explicit activities are secure and not overlapping"
            )

            challenges.forEach { challenge ->
                Text(text = challenge, fontSize = 16.sp, modifier = Modifier.padding(vertical = 4.dp))
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = onMainClick, modifier = Modifier.fillMaxWidth()) {
                Text("Main Activity")
            }
        }
    }
}
