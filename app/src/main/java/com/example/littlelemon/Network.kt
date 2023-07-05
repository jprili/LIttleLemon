package com.example.littlelemon

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class Network {
    private val url: String = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/" +
            "Working-With-Data-API/main/menu.json"


    val httpClient: HttpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }
    suspend fun getMenuItems(): List<MenuItemNetwork> {
        val response: MenuNetwork = this.httpClient.get(url).body()
        return response.items
    }
}

@Serializable
data class MenuNetwork(

    @SerialName("menu")
    val items: List<MenuItemNetwork>
    )

@Serializable
data class MenuItemNetwork(
    @SerialName("id")
    val id: Int,

    @SerialName("title")
    val title: String,

    @SerialName("description")
    val description: String,

    @SerialName("price")
    val price: Double,

    @SerialName("image")
    val image: String,

    @SerialName("category")
    val category: String
) {
    fun toMenuItemRoom()  = MenuItemRoom(
            id = id,
            title = title,
            description = description,
            price = price,
            image = image,
            category = category
        )
}

