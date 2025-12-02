package com.example.myfirstapp

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

private const val LAUNCH_TIMEOUT = 5000L

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class StartExplicitButtonTest {

    private lateinit var device: UiDevice

    @Before
    fun setUp() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        device = UiDevice.getInstance(instrumentation)

        // Start from the home screen.
        device.pressHome()

        // Wait for the launcher to appear.
        val launcherPackage = device.launcherPackageName
        assertNotNull("Launcher package is null", launcherPackage)
        device.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT
        )
    }

    @Test
    fun startExplicitButton_opensSecondActivityWithChallenge() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        val appPackage = context.packageName
        val appName = context.getString(R.string.app_name)

        // 1) Launch the app from the home screen by clicking the launcher icon.
        val intent = context.packageManager.getLaunchIntentForPackage(appPackage)
        requireNotNull(intent) { "Launch intent not found for package $appPackage" }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) // start a clean task
        context.startActivity(intent)

        // Wait for the app to appear.
        device.wait(
            Until.hasObject(By.text("Start Activity Explicitly")),
            LAUNCH_TIMEOUT
        )

        // 2) Click on the “Start Activity Explicitly” button.
        val explicitButton = device.findObject(By.text("Start Activity Explicitly"))
        assertNotNull("Explicit button not found", explicitButton)
        explicitButton.click()

        // 3) Check that the resulting activity shows one of the challenges.
        // Use a challenge string that you actually display in SecondActivity,
        // e.g. "Handling device fragmentation".
        val challengeTextSubstring = "Handling device fragmentation"

        device.wait(
            Until.hasObject(By.textContains(challengeTextSubstring)),
            LAUNCH_TIMEOUT
        )

        val challenge = device.findObject(By.textContains(challengeTextSubstring))
        assertNotNull(
            "Challenge text '$challengeTextSubstring' not found on SecondActivity",
            challenge
        )
    }
}
