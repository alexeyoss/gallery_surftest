package com.oss.gallery.utils

import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

object StringUtils {
    fun String.getFormattedDateFromTimestamp(): String {
        return try {
            val initialDate = SimpleDateFormat("dd-MM-yyyy", Locale("ru"))
            val finalDate = Date(this.toLong())
            return initialDate.format(finalDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }
}