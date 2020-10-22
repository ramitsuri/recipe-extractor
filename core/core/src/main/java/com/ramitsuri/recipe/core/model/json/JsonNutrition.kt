package com.ramitsuri.recipe.core.model.json

import com.google.gson.annotations.SerializedName

class JsonNutrition {

    @SerializedName("calories")
    var calories: String? = null

    @SerializedName("carbohydrateContent")
    var carbohydrates: String? = null

    @SerializedName("proteinContent")
    var protein: String? = null

    @SerializedName("fatContent")
    var fat: String? = null

    @SerializedName("saturatedFatContent")
    var saturatedFat: String? = null

    @SerializedName("cholesterolContent")
    var cholesterol: String? = null

    @SerializedName("sodiumContent")
    var sodium: String? = null

    @SerializedName("fiberContent")
    var fiber: String? = null

    @SerializedName("sugarContent")
    var sugar: String? = null

    @SerializedName("servingSize")
    var servingSize: String? = null
}
