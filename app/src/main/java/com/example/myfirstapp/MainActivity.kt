package com.example.myfirstapp

import android.content.Intent
import android.content.pm.PackageManager
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myfirstapp.ui.theme.MyFirstAppTheme

class MainActivity : ComponentActivity() {

    companion object {
        private const val PERMISSION_MSE412 = "com.example.myfirstapp.MSE412"
        private const val REQUEST_CODE_MSE412 = 412
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 🔐 Request custom dangerous permission at startup
        requestMse412PermissionIfNeeded()

        setContent {
            MyFirstAppTheme {
                MainScreen(
                    onExplicitClick = {
                        if (hasMse412Permission()) {
                            val intent = Intent(this, SecondActivity::class.java)
                            startActivity(intent)
                        } else {
                            // Optional: show a "permission denied" message
                        }
                    },
                    onImplicitClick = {
                        if (hasMse412Permission()) {
                            val intent = Intent("com.example.myfirstapp.OPEN_CHALLENGES")
                            startActivity(intent)
                        } else {
                            // Optional: notify user
                        }
                    },
                    onViewImageClick = {
                        val intent = Intent(this, ThirdActivity::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }

    // ✔ Check if permission is granted
    private fun hasMse412Permission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            PERMISSION_MSE412
        ) == PackageManager.PERMISSION_GRANTED
    }

    // ✔ Ask for permission only if needed
    private fun requestMse412PermissionIfNeeded() {
        if (!hasMse412Permission()) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(PERMISSION_MSE412),
                REQUEST_CODE_MSE412
            )
        }
    }

    // ✔ Handle the result (optional but recommended)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CODE_MSE412) {
            // You may add a Toast here if you want confirmation
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

            Text(text = "Full Name: Connor Nadgwick", fontSize = 22.sp)
            Text(text = "Student ID: 123456", fontSize = 20.sp, modifier = Modifier.padding(top = 8.dp))

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onExplicitClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Start Activity Explicitly")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onImplicitClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Start Activity Implicitly")
            }

            Spacer(modifier = Modifier.height(16.dp))

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
