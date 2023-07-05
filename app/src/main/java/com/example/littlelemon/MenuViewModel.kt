package com.example.littlelemon

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuViewModel(appCtx: Application) : AndroidViewModel(appCtx) {
    private val menuDatabase: MenuDatabase

    init {
        menuDatabase = Room.databaseBuilder(
            appCtx,
            MenuDatabase::class.java, "menu-database"
        ).build()
    }

    fun refreshMenu() {
        viewModelScope.launch(Dispatchers.IO) {
            if (menuDatabase.menuItemDao().isEmpty()) {
                val menuItems: List<MenuItemNetwork> = Network().getMenuItems()
                saveToDatabase(menuItems, menuDatabase)
            }
        }
    }

    fun saveToDatabase(menuItemNetwork: List<MenuItemNetwork>, menuDatabase: MenuDatabase) {
        val room : List<MenuItemRoom> = menuItemNetwork.map {
            it.toMenuItemRoom()
        }

        menuDatabase.menuItemDao().insertAll(*room.toTypedArray())
    }
}