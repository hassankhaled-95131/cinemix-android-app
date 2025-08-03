package com.example.cinemix.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cinemix.navigation.BottomNavGraph
import com.example.cinemix.ui.components.BottomNavigationBar

/**
 * الشاشة الرئيسية التي تحتوي على شريط التنقل السفلي.
 * تعمل كحاوية للشاشات الرئيسية (الرئيسية، البحث، المفضلة).
 */
@Composable
fun MainScreen(
    mainNavController: NavHostController // NavController الرئيسي للتطبيق
) {
    // NavController داخلي خاص بالشاشات الموجودة في شريط التنقل
    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = bottomNavController) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            // مخطط التنقل الخاص بالشاشات السفلية
            BottomNavGraph(
                mainNavController = mainNavController,
                bottomNavController = bottomNavController
            )
        }
    }
}
