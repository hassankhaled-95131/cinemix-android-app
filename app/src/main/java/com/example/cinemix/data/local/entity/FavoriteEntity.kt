package com.example.cinemix.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * يمثل كيان (جدول) المفضلة في قاعدة بيانات Room المحلية.
 * يستخدم لتخزين معرفات الأفلام التي أضافها المستخدم إلى مفضلته.
 */
@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey
    val movieId: Int
)
