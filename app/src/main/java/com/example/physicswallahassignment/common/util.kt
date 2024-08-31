package com.example.physicswallahassignment.common

import java.text.SimpleDateFormat
import java.util.Locale

fun formatApiDate(apiDate: String): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val date = formatter.parse(apiDate) ?: return ""
    val outputFormatter = SimpleDateFormat("yyyy-MM-dd, HH:mm", Locale.getDefault())
    return outputFormatter.format(date)
}
