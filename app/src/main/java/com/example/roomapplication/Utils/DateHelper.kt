package com.example.roomapplication.Utils

import java.text.SimpleDateFormat
import java.util.*

// make date current time
object DateHelper {
    fun getCurrentDate(): String {
        // make format date
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }
}