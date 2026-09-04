package com.example.parkee.core.common

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

fun String.toDisplayDate(): String {
    if (isBlank()) return "-"
    return try {
        val date = LocalDate.parse(this)
        date.format(DateTimeFormatter.ofPattern("d MMM yyyy", Locale.forLanguageTag("id")))
    } catch (e: DateTimeParseException) {
        this
    }
}