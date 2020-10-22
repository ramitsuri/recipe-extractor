package com.ramitsuri.recipe.core

import com.ramitsuri.recipe.core.model.domain.Recipe
import com.ramitsuri.recipe.core.serializer.SerializerHelper

private val URL_1 = "https://www.cookwithmanali.com/tawa-paneer/"
private val URL_2 = "https://www.bonappetit.com/recipe/kadhi-turmeric-yogurt-soup?verso=true"
private val URL_3 = "https://www.bbc.co.uk/food/recipes/creamy_chilli_bacon_and_60433"

fun main() {
    val extractor = RecipeExtractor()
    val recipe = extractor.get(URL_1)
    serializeTest(recipe)
}

fun serializeTest(recipe: Recipe?) {
    if (recipe == null) {
        println("Recipe null")
        return
    }
    val serializer = SerializerHelper()
    val serialized = serializer.serialize(recipe)
    val deserialized = serializer.deserialize(serialized, Recipe::class.java)
    println(serialized)
    if (deserialized?.name == recipe.name &&
            deserialized.authorName == recipe.authorName &&
            deserialized.url == recipe.url &&
            deserialized.description == recipe.description &&
            deserialized.publishDate.toEpochMilli() == recipe.publishDate.toEpochMilli() &&
            deserialized.imageUrl == recipe.imageUrl &&
            deserialized.yields == recipe.yields &&
            deserialized.time.cookTime.toMillis() == recipe.time.cookTime.toMillis() &&
            deserialized.time.prepTime.toMillis() == recipe.time.prepTime.toMillis() &&
            deserialized.ingredients.size == recipe.ingredients.size &&
            deserialized.sections.size == recipe.sections.size &&
            deserialized.keywords.size == recipe.keywords.size) {
        println("Matches after serialization/deserialization")
    } else {
        println("Does not match after serialization/deserialization")
    }
}

