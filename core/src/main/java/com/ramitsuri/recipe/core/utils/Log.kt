package com.ramitsuri.recipe.core.utils

import java.time.LocalDateTime

class Log {
    companion object {
        @JvmStatic
        fun d(tag: String, message: String) {
            val date = LocalDateTime.now().toString()
            val packageName = Log::class.java.`package`.name
            System.out.printf("%1s %2s D/%3s: %4s%n", date, packageName, tag, message)
        }
    }
}
