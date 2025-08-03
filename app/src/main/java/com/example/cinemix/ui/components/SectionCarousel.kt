package com.example.cinemix.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cinemix.domain.model.Movie
import com.example.cinemix.ui.theme.AccentPrimary

/**
 * مكون Composable لعرض قسم كامل مع عنوان وقائمة أفقية من الأفلام.
 *
 * @param title عنوان القسم (e.g., "الأفلام الشائعة").
 * @param movies قائمة الأفلام التي سيتم عرضها في الشريط.
 * @param onMovieClick دالة سيتم استدعاؤها عند النقر على أي بطاقة فيلم.
 * @param onViewAllClick دالة سيتم استدعاؤها عند النقر على زر "عرض الكل".
 */
@Composable
fun SectionCarousel(
    title: String,
    movies: List<Movie>,
    onMovieClick: (Int) -> Unit,
    onViewAllClick: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = "عرض الكل",
                modifier = Modifier.clickable { onViewAllClick() },
                style = MaterialTheme.typography.bodyMedium,
                color = AccentPrimary
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(movies) { movie ->
                Box(modifier = Modifier.width(140.dp)) {
                    MovieCard(
                        movie = movie,
                        onMovieClick = onMovieClick
                    )
                }
            }
        }
    }
}
