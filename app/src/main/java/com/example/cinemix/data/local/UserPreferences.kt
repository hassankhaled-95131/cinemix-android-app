package com.example.cinemix.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * مدير لتخزين وإدارة تفضيلات المستخدم باستخدام Jetpack DataStore.
 * يستخدم لتخزين بيانات بسيطة مثل حالة تسجيل الدخول.
 *
 * @param context سياق التطبيق للوصول إلى DataStore.
 */
class UserPreferences(private val context: Context) {

    // تفويض لإنشاء مثيل واحد من DataStore على مستوى التطبيق.
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_settings")
        private val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")
    }

    /**
     * Flow لقراءة حالة تسجيل الدخول بشكل تفاعلي (Reactive).
     * سيتم إرسال قيمة جديدة تلقائياً عند تغيير الحالة.
     */
    val isLoggedInFlow: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_LOGGED_IN_KEY] ?: false
        }

    /**
     * دالة معلقة (suspend function) لحفظ حالة تسجيل الدخول.
     * @param isLoggedIn القيمة الجديدة لحالة تسجيل الدخول.
     */
    suspend fun saveLoginState(isLoggedIn: Boolean) {
        context.dataStore.edit { settings ->
            settings[IS_LOGGED_IN_KEY] = isLoggedIn
        }
    }
}
