package com.example.cinemix.util

/**
 * فئة مغلفة (sealed class) لتمثيل حالات جلب البيانات المختلفة.
 * تُستخدم لتمرير حالة البيانات (نجاح، خطأ، تحميل) مع البيانات نفسها
 * من طبقة البيانات (Data Layer) إلى طبقة واجهة المستخدم (UI Layer).
 *
 * @param T نوع البيانات التي يتم جلبها.
 * @property data البيانات الفعلية (قد تكون null في حالة الخطأ أو التحميل).
 * @property message رسالة الخطأ (تكون null في حالة النجاح).
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    /**
     * تمثل حالة النجاح في جلب البيانات.
     * @param data البيانات التي تم جلبها بنجاح (لا يمكن أن تكون null).
     */
    class Success<T>(data: T) : Resource<T>(data)

    /**
     * تمثل حالة الفشل في جلب البيانات.
     * @param message رسالة الخطأ التي تصف سبب الفشل.
     * @param data بيانات قديمة (اختياري) يمكن عرضها أثناء ظهور رسالة الخطأ.
     */
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)

    /**
     * تمثل حالة أن البيانات قيد التحميل.
     * @param data بيانات قديمة (اختياري) يمكن عرضها أثناء التحميل.
     */
    class Loading<T>(data: T? = null) : Resource<T>(data)
}
