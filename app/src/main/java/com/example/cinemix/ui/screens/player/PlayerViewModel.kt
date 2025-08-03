package com.example.cinemix.ui.screens.player

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * يمثل الحالة الحالية لواجهة المستخدم لشاشة مشغل الفيديو.
 *
 * @property videoUrl رابط الفيديو الذي سيتم تشغيله.
 */
data class PlayerState(
    val videoUrl: String? = null
)

/**
 * ViewModel الخاص بشاشة مشغل الفيديو.
 * مسؤول عن توفير رابط الفيديو لواجهة المستخدم.
 */
@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(PlayerState())
    val uiState = _uiState.asStateFlow()

    init {
        // في تطبيق حقيقي، ستقوم هنا بجلب رابط الفيديو من الخادم الخاص بك
        // بناءً على معرّف الفيلم القادم من savedStateHandle.

        // حالياً، سنستخدم رابط فيديو تجريبي ثابت لأغراض العرض.
        // هذا الرابط هو بث HLS تجريبي من Apple.
        _uiState.value = PlayerState(videoUrl = "http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8")
    }
}
