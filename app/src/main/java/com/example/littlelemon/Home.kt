package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Home(context: Context, navController: NavController) {
    Header(navController)
    Hero(context = context)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Hero(context: Context) {
    var searchPhrase by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .background(Color(0xFF495E57))
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Text(
            text = context.resources.getString(R.string.rest_name),
            color = Color(0xFFF4C314)
        )
        Text(
            text = context.resources.getString(R.string.rest_city),
            color = Color.White
        )
        Row(Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = context.resources.getString(R.string.rest_desc),
                modifier = Modifier.fillMaxWidth(0.7f)
            )
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "Hero Image",
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp)
                    )
            )
        }

        Spacer(
            modifier = Modifier.size(10.dp)
        )

        TextField(
            value = searchPhrase,
            onValueChange = {
                searchPhrase = it
                search(searchPhrase)
            },
            placeholder = {
                Text(text = "Enter Search Phrase")
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

fun search(phrase: String) {

}

@Preview
@Composable
fun PreviewHome() {
    val navController: NavHostController = rememberNavController()
    //Home(navController = navController)
}