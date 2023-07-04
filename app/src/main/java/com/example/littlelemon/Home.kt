package com.example.littlelemon

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun Home(navController: NavController) {
    Button(
        onClick = {
            navController.navigate(Profile.route)
        }
    ) {
        Text(text = "Profile")
    }

}

@Preview
@Composable
fun PreviewHome() {

}