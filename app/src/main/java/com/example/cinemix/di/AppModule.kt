package com.example.cinemix.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.cinemix.data.local.AppDatabase
import com.example.cinemix.data.local.UserPreferences
import com.example.cinemix.data.remote.ApiService
import com.example.cinemix.data.repository.AuthRepositoryImpl
import com.example.cinemix.data.repository.MovieRepositoryImpl
import com.example.cinemix.domain.repository.AuthRepository
import com.example.cinemix.domain.repository.MovieRepository
import com.example.cinemix.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * وحدة Hilt لتوفير التبعيات على مستوى التطبيق (Singleton).
 * تخبر Hilt كيفية إنشاء وتوفير مثيلات (Instances) من الكائنات الهامة.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // --- Network Dependencies ---

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.TMDB_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    // --- Local Database Dependencies ---

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "cinemix.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(db: AppDatabase) = db.movieDao()

    // --- Local Preferences Dependencies ---

    @Provides
    @Singleton
    fun provideUserPreferences(@ApplicationContext context: Context): UserPreferences {
        return UserPreferences(context)
    }

    // --- Repository Dependencies ---

    @Provides
    @Singleton
    fun provideMovieRepository(api: ApiService, dao: com.example.cinemix.data.local.MovieDao): MovieRepository {
        return MovieRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(userPreferences: UserPreferences): AuthRepository {
        return AuthRepositoryImpl(userPreferences)
    }
}
