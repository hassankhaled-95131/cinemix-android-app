package com.example.cinemix.util

/**
 * ملف لتخزين الثوابت المستخدمة في جميع أنحاء التطبيق.
 * هذا يسهل إدارة وتغيير القيم الأساسية من مكان واحد.
 */
object Constants {

    // الرابط الأساسي لواجهة برمجة تطبيقات موقعك (PHP Backend)
    // يجب استبداله بالدومين الفعلي لموقعك
    const val CINEMIX_API_BASE_URL = "https://mixplus.site/api/"

    // الرابط الأساسي لواجهة برمجة تطبيقات TMDB
    const val TMDB_API_BASE_URL = "https://api.themoviedb.org/3/"

    // مفتاح الوصول لواجهة برمجة تطبيقات TMDB
    const val TMDB_API_KEY = "2ce40d57fec2f7171df6a85bdeafff13"

    // الرابط الأساسي لجلب الصور المصغرة (Posters) بحجم 500
    const val TMDB_IMAGE_BASE_URL_W500 = "https://image.tmdb.org/t/p/w500"

    // الرابط الأساسي لجلب الصور الخلفية (Backdrops) بالحجم الأصلي
    const val TMDB_BACKDROP_IMAGE_BASE_URL_ORIGINAL = "https://image.tmdb.org/t/p/original"

}
