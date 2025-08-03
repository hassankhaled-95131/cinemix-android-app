package com.example.cinemix

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * فئة التطبيق الرئيسية (Application Class).
 * يتم استخدامها كنقطة دخول لتهيئة المكتبات على مستوى التطبيق.
 *
 * @HiltAndroidApp: هذه العلامة (Annotation) تخبر Hilt بأن هذه هي فئة التطبيق الرئيسية
 * وتبدأ عملية توليد الكود اللازم لحقن التبعيات.
 */
@HiltAndroidApp
class CineMixApplication : Application() {
    // حالياً، لا نحتاج إلى إضافة أي كود تهيئة مخصص هنا.
    // وجود العلامة @HiltAndroidApp كافٍ لعمل Hilt.
}
