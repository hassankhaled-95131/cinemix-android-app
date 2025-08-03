package com.example.cinemix.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * يمثل عنصرًا واحدًا في شريط التنقل السفلي.
 *
 * @property title اسم العنصر الذي سيظهر (اختياري).
 * @property icon أيقونة العنصر.
 * @property screen المسار (Route) المرتبط بهذا العنصر من فئة Screen.
 */
data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
) {
    companion object {
        /**
         * قائمة تحتوي على جميع عناصر شريط التنقل السفلي.
         */
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
