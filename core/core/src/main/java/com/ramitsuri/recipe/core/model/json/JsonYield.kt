package com.ramitsuri.recipe.core.model.json

import com.ramitsuri.recipe.core.model.interfaces.Yield
import com.ramitsuri.recipe.core.utils.ParseHelper

class JsonYield : Yield {
    var quantity: Double? = 0.0

    fun fromStringArray(yields: Array<String>?): JsonYield {
        if (yields == null || yields.isEmpty()) {
            quantity = 0.0
            return this
        }
        return fromString(yields[0])
    }

    fun fromString(yields: String?): JsonYield {
        if (yields == null) {
            quantity = 0.0
            return this
        }
        val parser = ParseHelper()
        quantity = parser.fromJsonYields(yields)
        return this
    }
}
