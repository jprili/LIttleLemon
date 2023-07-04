package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Home(navController: NavController) {
    Header(navController)
}

@Composable
private fun Header(navController: NavController) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxHeight(0.1f)
    ) {
        Spacer(
            modifier = Modifier.width(60.dp)
        )

        Image(
            painter = painterResource(
                id = R.drawable.logo
            ),
            contentDescription = "logo",
            modifier = Modifier
                .size(230.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "profile",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .padding(10.dp)
                .clickable { navController.navigate(Profile.route) }
        )
    }
}

@Preview
@Composable
fun PreviewHome() {
    val navController: NavHostController = rememberNavController()
    Home(navController = navController)
}