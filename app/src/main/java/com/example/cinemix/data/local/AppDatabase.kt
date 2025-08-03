package com.example.cinemix.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cinemix.data.local.entity.FavoriteEntity
import com.example.cinemix.data.local.entity.MovieEntity

/**
 * الفئة المجردة (Abstract Class) التي تمثل قاعدة بيانات Room للتطبيق.
 * هذه الفئة تجمع كل الكيانات (الجداول) وواجهات الوصول للبيانات (DAOs).
 *
 * @property entities قائمة بجميع فئات الكيانات التي ستكون جزءاً من قاعدة البيانات.
 * @property version رقم إصدار قاعدة البيانات. يجب زيادته عند إجراء أي تغييرات على بنية الجداول.
 */
@Database(
    entities = [MovieEntity::class, FavoriteEntity::class],
    version = 1,
    exportSchema = false // لتعطيل تصدير مخطط قاعدة البيانات
)
abstract class AppDatabase : RoomDatabase() {

    /**
     * دالة مجردة توفر وصولاً إلى MovieDao.
     * ستقوم Room بتوليد التنفيذ الفعلي لهذه الدالة.
     */
    abstract fun movieDao(): MovieDao

}
