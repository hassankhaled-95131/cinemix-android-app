package com.example.cinemix.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cinemix.navigation.BottomNavItem

/**
 * مكون Composable لعرض شريط التنقل السفلي.
 *
 * @param navController وحدة التحكم في التنقل لمعرفة الشاشة الحالية والتعامل مع النقرات.
 */
@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val items = BottomNavItem.getBottomNavItems()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        // تجنب إضافة نفس الشاشة إلى المكدس عدة مرات
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // استعادة الحالة عند العودة إلى شاشة
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
