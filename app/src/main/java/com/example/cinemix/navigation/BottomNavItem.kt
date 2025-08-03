package com.example.cinemix.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
) {
    companion object {
        fun getBottomNavItems(): List<BottomNavItem> {
            return listOf(
                BottomNavItem(
                    title = "الرئيسية",
                    icon = Icons.Default.Home,
                    screen = Screen.Home
                ),
                BottomNavItem(
                    title = "البحث",
                    icon = Icons.Default.Search,
                    screen = Screen.Search
                ),
                BottomNavItem(
                    title = "الفئات",
                    icon = Icons.Default.VideoLibrary,
                    screen = Screen.Categories
                ),
                BottomNavItem(
                    title = "المفضلة",
                    icon = Icons.Default.Favorite,
                    screen = Screen.Favorites
                ),
                BottomNavItem(
                    title = "ملفي",
                    icon = Icons.Default.Person,
                    screen = Screen.Profile
                )
            )
        }
    }
}
