package com.ramitsuri.recipe.core.model.json

import com.google.gson.annotations.SerializedName
import com.ramitsuri.recipe.core.model.interfaces.Author
import com.ramitsuri.recipe.core.model.interfaces.Instruction
import com.ramitsuri.recipe.core.model.interfaces.Keywords
import com.ramitsuri.recipe.core.model.interfaces.Yield
import com.squareup.moshi.Json

class JsonRecipe {

    @SerializedName("@type")
    var type: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("author")
    var author: Author? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("datePublished")
    var publishDate: String? = null

    @SerializedName("image")
    var images: Array<String>? = null

    @SerializedName("recipeYield")
    var yields: Yield? = null

    @SerializedName("prepTime")
    var prepTime: String? = null

    @SerializedName("cookTime")
    var cookTime: String? = null

    @SerializedName("recipeIngredient")
    var ingredients: Array<String>? = null

    @SerializedName("recipeInstructions")
    var jsonInstructions: List<Instruction> = emptyList()

    @Json(name = "keywords")
    var keywords: Keywords? = null

    @SerializedName("nutrition")
    var jsonNutrition: JsonNutrition? = null

    @SerializedName("mainEntityOfPage")
    var url: String? = null
}
