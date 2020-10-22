package com.ramitsuri.recipe.core.model.domain

import com.ramitsuri.recipe.core.constants.QuantityUnit

data class Ingredient(val ingredient: String) {
    var quantity: Double? = null
    var unit: QuantityUnit? = null
}
