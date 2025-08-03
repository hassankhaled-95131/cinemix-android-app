package com.example.cinemix.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cinemix.data.local.entity.FavoriteEntity
import com.example.cinemix.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

/**
 * واجهة الوصول للبيانات (DAO) لقاعدة بيانات Room.
 * تحتوي على دوال لتنفيذ استعلامات SQL على الجداول.
 */
@Dao
interface MovieDao {

    // --- Operations for MovieEntity (Caching) ---

    /**
     * إدراج قائمة من الأفلام في جدول 'movies'.
     * إذا كان الفيلم موجوداً بالفعل (نفس الـ id)، سيتم استبداله.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    /**
     * حذف جميع الأفلام التي تنتمي إلى وسم نوع معين (e.g., "popular_movie").
     * يُستخدم لمسح الكاش القديم قبل إدراج بيانات جديدة.
     */
    @Query("DELETE FROM movies WHERE typeTag = :typeTag")
    suspend fun clearMoviesByType(typeTag: String)

    /**
     * جلب جميع الأفلام التي تنتمي إلى وسم نوع معين.
     */
    @Query("SELECT * FROM movies WHERE typeTag = :typeTag")
    suspend fun getMoviesByType(typeTag: String): List<MovieEntity>


    // --- Operations for FavoriteEntity (User Favorites) ---

    /**
     * إدراج فيلم جديد إلى جدول المفضلة.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

    /**
     * حذف فيلم من جدول المفضلة بناءً على معرّفه.
     */
    @Query("DELETE FROM favorites WHERE movieId = :movieId")
    suspend fun deleteFavorite(movieId: Int)

    /**
     * جلب جميع معرفات الأفلام المفضلة.
     * استخدام Flow يضمن أن أي تغييرات في الجدول (إضافة/حذف)
     * سيتم إرسالها تلقائياً إلى المراقبين (Observers) في واجهة المستخدم.
     */
    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

}
