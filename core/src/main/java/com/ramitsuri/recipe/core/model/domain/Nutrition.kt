package com.ramitsuri.recipe.core.model.domain

import com.ramitsuri.recipe.core.model.json.JsonNutrition

class Nutrition(jsonNutrition: JsonNutrition?) {
    val calories = jsonNutrition?.calories ?: ""
    val carbohydrates = jsonNutrition?.carbohydrates ?: ""
    val protein = jsonNutrition?.protein ?: ""
    val fat = jsonNutrition?.fat ?: ""
    val saturatedFat = jsonNutrition?.saturatedFat ?: ""
    val cholesterol = jsonNutrition?.cholesterol ?: ""
    val sodium = jsonNutrition?.sodium ?: ""
    val fiber = jsonNutrition?.fiber ?: ""
    val sugar = jsonNutrition?.sugar ?: ""
    val servingSize = jsonNutrition?.servingSize ?: ""
}
