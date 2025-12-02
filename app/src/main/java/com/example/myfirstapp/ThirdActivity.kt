package com.example.myfirstapp

import android.os.Bundle
import android.graphics.Bitmap
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.example.myfirstapp.ui.theme.MyFirstAppTheme

class ThirdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFirstAppTheme {
                ImageCaptureScreen()
            }
        }
    }
}

@Composable
private fun ImageCaptureScreen() {
    var photo by remember { mutableStateOf<Bitmap?>(null) }

    // Opens the device camera app and returns a small Bitmap (no storage permission needed)
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bmp: Bitmap? ->
        photo = bmp
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { cameraLauncher.launch(null) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Capture Image")
            }

            Spacer(Modifier.height(16.dp))

            if (photo != null) {
                // Display the returned thumbnail below the button
                Image(
                    bitmap = photo!!.asImageBitmap(),
                    contentDescription = "Captured image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 240.dp)
                )
            } else {
                Text("No image captured yet.", modifier = Modifier.padding(top = 8.dp))
            }
        }
    }
}
