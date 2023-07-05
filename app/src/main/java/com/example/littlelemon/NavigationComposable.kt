package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(context: Context, navController: NavHostController) {
    val startDestination: String
    val sharedPrefs = context.getSharedPreferences(
        "littleLemon",
        Context.MODE_PRIVATE
    )
    val isRegistered: Boolean = sharedPrefs.getBoolean("isRegistered", false)

    startDestination =
        if (isRegistered) {
            Home.route
        } else {
            Onboarding.route
        }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Onboarding.route) {
            Onboarding(context, navController = navController)
        }

        composable(Home.route) {
            Home(context, navController = navController)
        }
        
        composable(Profile.route) {
            Profile(context, navController = navController)
        }
    }
}
