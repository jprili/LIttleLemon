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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi

@Composable
fun Home(context: Context, navController: NavController) {
    val menuViewModel: MenuViewModel = viewModel()
    var searchText: String by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        menuViewModel.refreshMenu()
    }

    Column {
        Header(navController)
        Hero(context = context, onSearch = { searchText = it })
        MenuItemsColumn(searchText = searchText)
    }
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

@Composable
fun Hero(context: Context, onSearch: (String) -> Unit ) {
    var searchText: String by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF495E57))
            .padding(bottom = 20.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Text(
                    text = context.resources.getString(R.string.rest_name),
                    fontFamily = FontFamily(Font(R.font.markazi_text_regular)),
                    fontSize = 32.sp,
                    color = Color(0xFFF4C314)
                )
                Text(
                    text = context.resources.getString(R.string.rest_city),
                    fontFamily = FontFamily(Font(R.font.markazi_text_regular)),
                    fontSize = 28.sp,
                    color = Color.White
                )

                Text(
                    text = context.resources.getString(R.string.rest_desc),
                    modifier = Modifier.fillMaxWidth(0.5f),
                    color = Color(0xFFEDEFEE)
                )
            }

            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "Hero Image",
                modifier = Modifier
                    .padding(10.dp)
                    .clip(
                        RoundedCornerShape(16.dp)
                    )
            )
        }

        Spacer(
            modifier = Modifier.size(10.dp)
        )

        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
                onSearch(searchText)
            },
            placeholder = {
                Text(text = "Search Menu")
            },
            maxLines = 1,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .clip(
                    RoundedCornerShape(16.dp)
                )
        )
    }
}

@Composable
fun MenuItemsColumn(searchText: String) {
    val menuViewModel: MenuViewModel = viewModel()
    val menuItemRooms: List<MenuItemRoom> = menuViewModel
        .getMenuItems()
        .observeAsState(listOf()).value

    val menuItemsFiltered: List<MenuItemRoom> = if (searchText.isNotEmpty()) {
        menuItemRooms.filter { it.title.contains(searchText, ignoreCase = true) }
    } else { menuItemRooms }

    LazyColumn {
        items(menuItemsFiltered) {
            MenuCard(menuItem = it)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuCard(menuItem: MenuItemRoom) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .padding(10.dp)
    ) {
        Row {
            Column {
                Text(
                    text = menuItem.title,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.karla_regular)),
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = menuItem.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            
            Spacer(modifier = Modifier.size(10.dp))
            
/*            GlideImage(
                model = menuItem.image,
                contentDescription = menuItem.title,
                contentScale = ContentScale.Crop
            ) */
        }
    }

}

@Preview
@Composable
fun PreviewHome() {
}