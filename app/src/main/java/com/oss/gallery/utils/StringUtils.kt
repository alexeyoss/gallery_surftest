package com.oss.gallery.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object StringUtils {
    fun String.getFormattedDateFromTimestamp(): String {
        return try {
            val initialDate = SimpleDateFormat("dd-MM-yyyy", Locale("ru"))
            val finalDate = Date(this.toLong())
            initialDate.format(finalDate)
        } catch (e: Exception) {
            e.toString()
        }
    }
}