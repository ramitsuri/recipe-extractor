package com.ramitsuri.recipe.core.model.domain

import com.ramitsuri.recipe.core.model.json.*
import com.ramitsuri.recipe.core.utils.ParseHelper
import java.time.Instant

class Recipe(jsonRecipe: JsonRecipe?) {

    val name: String

    val authorName: String

    var url: String

    val description: String

    val publishDate: Instant

    val imageUrl: String

    val yields: Double

    val time: Time

    val ingredients: List<Ingredient>

    val sections: List<InstructionSection>

    val keywords: List<String>

    val nutrition: Nutrition

    init {
        val parser = ParseHelper()

        name = jsonRecipe?.name ?: ""
        authorName = (jsonRecipe?.author as? JsonAuthor)?.name ?: ""
        url = jsonRecipe?.url ?: ""
        description = jsonRecipe?.description ?: ""

        publishDate = parser.fromJsonDate(jsonRecipe?.publishDate)

        imageUrl = parser.fromJsonImages(jsonRecipe?.images)

        yields = (jsonRecipe?.yields as? JsonYield)?.quantity ?: 0.0

        val prepTime = parser.fromJsonPrepTime(jsonRecipe?.prepTime)
        val cookTime = parser.fromJsonPrepTime(jsonRecipe?.cookTime)
        time = Time(prepTime, cookTime)

        ingredients = ArrayList()
        if (jsonRecipe?.ingredients != null) {
            for (ingredient in jsonRecipe.ingredients!!) {
                ingredients.add(Ingredient(ingredient))
            }
        }

        sections = ArrayList()
        if (jsonRecipe?.jsonInstructions?.size!! > 0) {
            if (jsonRecipe.jsonInstructions[0] is JsonInstructionSection) {
                // Could be multiple sections
                for (instruction in jsonRecipe.jsonInstructions) {
                    val jsonInstructionSection = instruction as JsonInstructionSection
                    val section = InstructionSection(
                            jsonInstructionSection.name ?: "Section",
                            jsonInstructionSection.instructions)
                    sections.add(section)
                }
            } else if (jsonRecipe.jsonInstructions[0] is JsonInstructionStep) {
                // Only one section
                val section = InstructionSection("Main recipe",
                        jsonRecipe.jsonInstructions)
                sections.add(section)
            }
        }


        keywords = mutableListOf()
        if (jsonRecipe.keywords != null) {
            keywords.addAll((jsonRecipe.keywords as JsonKeywords).keywords)
        }

        nutrition = Nutrition(jsonRecipe.jsonNutrition)
    }
}
