package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Profile(context: Context, navController: NavController) {
    val sharedPrefs: SharedPreferences = context.getSharedPreferences(
        "littleLemon",
        Context.MODE_PRIVATE
    )

    val firstName: String = sharedPrefs.getString("firstName", "") ?: ""
    val lastName: String = sharedPrefs.getString("lastName", "") ?: ""
    val email: String = sharedPrefs.getString("email", "") ?: ""

    Column {
        Image(
            painter = painterResource(
                id = R.drawable.logo
            ),
            contentDescription = "logo",
            modifier = Modifier
                .fillMaxWidth()
                .size(90.dp)
                .padding(20.dp)
        )

        Text(
            text = "Personal Information",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            textAlign = TextAlign.Left,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 80.dp, bottom = 80.dp)
        )

        Column { // Form
            ProfileInfo(
                txt = "First Name",
                value = firstName
            )

            ProfileInfo(
                txt = "Last Name",
                value = lastName
            )

            ProfileInfo(
                txt = "Email",
                value = email
            )
        }

        Button(
            onClick = {
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
            },
            colors = ButtonDefaults.buttonColors(Color(0xFFF4C314)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 200.dp, bottom = 40.dp, start = 10.dp, end = 10.dp)
        ) {
            Text(
                text = "Logout",
                color = Color(0xFF333333)
            )
        }
    }
}

@Composable
fun ProfileInfo(txt: String, value: String) {
    Column(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
    ) {
        Text(
            text = txt,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = value,
            fontSize = 16.sp,
            color = Color(0xFF333333),
            modifier = Modifier
                .padding(bottom = 40.dp)
        )
    }
}

@Preview
@Composable
fun PreviewProfile() {

}