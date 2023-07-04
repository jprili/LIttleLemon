package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun Profile(context: Context, navController: NavController) {
    val sharedPrefs: SharedPreferences = context.getSharedPreferences(
        "littleLemon",
        Context.MODE_PRIVATE
    )
    Button(onClick = {
        sharedPrefs
            .edit()
            .putBoolean("isRegistered", false)
            .apply()

        navController.navigate(Onboarding.route) {
            popUpTo(Home.route) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }) {
        Text(
            text = "Logout"
        )
    }
}

@Preview
@Composable
fun PreviewProfile() {

}