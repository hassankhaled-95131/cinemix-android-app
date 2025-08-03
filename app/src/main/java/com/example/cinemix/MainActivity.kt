package com.example.cinemix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.cinemix.navigation.AppNavigation
import com.example.cinemix.ui.theme.CineMixTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * النشاط الرئيسي والوحيد في التطبيق (Single-Activity Architecture).
 * يعمل كنقطة دخول للتطبيق ويستضيف جميع واجهات المستخدم المبنية باستخدام Jetpack Compose.
 *
 * @AndroidEntryPoint: هذه العلامة (Annotation) تسمح لـ Hilt بحقن التبعيات في هذا النشاط.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContent هو المكان الذي يتم فيه تعريف واجهة المستخدم باستخدام Composable functions.
        setContent {
            // تطبيق المظهر المخصص (الألوان، الخطوط) على التطبيق بأكمله.
            CineMixTheme {
                // Surface هي حاوية أساسية من Material Design.
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // استدعاء Composable الخاص بالتنقل، والذي سيدير عرض الشاشات.
                    AppNavigation()
                }
            }
        }
    }
}
