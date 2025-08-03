package com.example.cinemix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.cinemix.R // سيتم إنشاء هذا الملف تلقائياً عند إضافة أول مورد
import com.example.cinemix.domain.model.Movie
import com.example.cinemix.ui.theme.CineMixTheme
import com.example.cinemix.util.Constants

/**
 * مكون Composable لعرض بطاقة فيلم قابلة لإعادة الاستخدام.
 *
 * @param movie كائن الفيلم الذي سيتم عرضه.
 * @param onMovieClick دالة سيتم استدعاؤها عند النقر على البطاقة.
 */
@Composable
fun MovieCard(
    movie: Movie,
    onMovieClick: (Int) -> Unit
) {
    val imageUrl = "${Constants.TMDB_IMAGE_BASE_URL_W500}${movie.posterPath}"

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        // صورة احتياطية في حالة عدم توفر صورة للفيلم
        error = painterResource(id = R.drawable.ic_placeholder),
        placeholder = painterResource(id = R.drawable.ic_placeholder)
    )

    Card(
        modifier = Modifier
            .aspectRatio(2f / 3f) // للحفاظ على أبعاد البوستر
            .clickable { onMovieClick(movie.id) },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // عرض تأثير الشيمر أثناء تحميل الصورة
            if (painter.state is AsyncImagePainter.State.Loading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.LightGray.copy(alpha = 0.5f), Color.Gray.copy(alpha = 0.5f))
                            )
                        )
                )
            }

            Image(
                painter = painter,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

// دالة لمعاينة شكل البطاقة في Android Studio
@Preview
@Composable
fun MovieCardPreview() {
    CineMixTheme {
        val sampleMovie = Movie(
            id = 1,
            title = "Sample Movie",
            overview = "",
            posterPath = "/qA5kPY9b0MXdVJ60D6x4Ldsm3j.jpg",
            backdropPath = "",
            voteAverage = 8.5,
            releaseDate = "2023-10-25",
            mediaType = "movie",
            genres = emptyList(),
            runtime = 120
        )
        MovieCard(movie = sampleMovie, onMovieClick = {})
    }
}
