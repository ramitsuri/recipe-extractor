package com.ramitsuri.recipe.core.utils

import org.apache.commons.io.FilenameUtils
import java.net.MalformedURLException
import java.net.URL
import java.time.Duration
import java.time.Instant
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class ParseHelper {

    fun fromJsonDate(jsonDate: String?): Instant {
        if (jsonDate == null) {
            return Instant.now()
        }
        var instant: Instant
        try {
            val timeFormatter = DateTimeFormatter.ISO_DATE_TIME
            val offsetDateTime = OffsetDateTime.parse(jsonDate, timeFormatter)
            instant = Instant.from(offsetDateTime)
        } catch (e: Exception) {
            e.printStackTrace()
            instant = Instant.now()
        }
        return instant
    }

    fun fromJsonImages(images: Array<String>?): String {
        if (images == null) {
            return ""
        }
        var imageUrl = ""
        for (image in images) {
            try {
                val url = URL(image)
                val imageName = FilenameUtils.getName(url.path)
                if (!imageName.matches("\\d+[x]\\d+[.]".toRegex())) {
                    imageUrl = image
                    break
                }
            } catch (e: MalformedURLException) {
                e.printStackTrace()
                imageUrl = ""
            }
        }
        return imageUrl
    }

    fun fromJsonPrepTime(time: String?): Duration {
        if (time == null) {
            return Duration.ZERO
        }
        var duration: Duration
        try {
            duration = Duration.parse(time)
        } catch (e: Exception) {
            e.printStackTrace()
            duration = Duration.ZERO
        }
        return duration
    }

    fun fromJsonYields(yields: String): Double {
        var value: Double
        try {
            val valString = StringBuilder()
            var digitAdded = false
            var decimalAdded = false
            for (c in yields.toCharArray()) {
                if (Character.isDigit(c)) {
                    valString.append(c)
                    digitAdded = true
                }
                if (c == '.' && digitAdded && !decimalAdded) {
                    valString.append(c)
                    decimalAdded = true
                }
            }
            value = valString.toString().toDouble()
        } catch (e: Exception) {
            value = 0.0
        }
        return value
    }
}
