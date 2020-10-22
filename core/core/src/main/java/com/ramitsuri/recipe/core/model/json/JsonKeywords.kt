package com.ramitsuri.recipe.core.model.json

import com.ramitsuri.recipe.core.model.interfaces.Keywords

class JsonKeywords : Keywords {
    var keywords = mutableListOf<String>()

    fun fromString(text: String?): JsonKeywords {
        if (text != null) {
            val texts = text.split(",")
            return fromStringArray(texts.toMutableList())
        }
        return this
    }

    fun fromStringArray(texts: MutableList<String>?): JsonKeywords {
        if (texts != null) {
            keywords = texts
        }
        return this
    }
}