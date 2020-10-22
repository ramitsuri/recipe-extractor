package com.ramitsuri.recipe.core

import com.ramitsuri.recipe.core.constants.Constants
import com.ramitsuri.recipe.core.html.HtmlExtractor
import com.ramitsuri.recipe.core.model.domain.Recipe
import com.ramitsuri.recipe.core.model.json.JsonRecipe
import com.ramitsuri.recipe.core.serializer.SerializerHelper
import com.ramitsuri.recipe.core.utils.Log
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener

class RecipeExtractor {
    private val TAG = RecipeExtractor::class.qualifiedName ?: "RecipeExtractor"

    fun get(url: String): Recipe? {
        return getRecipe(url)
    }

    fun getJson(url: String): String? {
        val recipe = getRecipe(url)
        if (recipe != null) {
            val serializer = SerializerHelper()
            return serializer.serialize(recipe)
        }
        return null
    }

    fun getRecipe(url: String): Recipe? {
        val htmlExtractor = HtmlExtractor()
        val key = Constants.HtmlKeys.TYPE
        val value = Constants.HtmlKeys.JSON_LD
        val json = htmlExtractor.extractByAttributeValue(url, key, value) ?: return null

        val recipeJson = findRecipe(json) ?: return null
        Log.d(TAG, recipeJson)

        val serializer = SerializerHelper()
        val jsonRecipe = serializer.deserialize(recipeJson, JsonRecipe::class.java) ?: return null

        val recipe = Recipe(jsonRecipe)
        recipe.url = url
        return recipe
    }

    fun findRecipe(jsonString: String): String? {
        val value = JSONTokener(jsonString).nextValue()
        if (value is JSONArray) {
            return findRecipe(value)
        } else if (value is JSONObject) {
            var recipe = findRecipe(value)
            if (recipe != null) {
                return recipe
            } else {
                for (key in value.keySet()) {
                    if (value.get(key) is JSONArray) {
                        val jsonArray = value.get(key) as JSONArray
                        recipe = findRecipe(jsonArray)
                        if (recipe != null) {
                            return recipe
                        }
                    }
                }
            }
        }
        return null
    }

    private fun findRecipe(jsonObject: JSONObject): String? {
        if (jsonObject.has(Constants.JsonKeys.TYPE) &&
                jsonObject.get(Constants.JsonKeys.TYPE) is String) {
            val type = jsonObject.get(Constants.JsonKeys.TYPE)
            if (type == Constants.JsonKeys.RECIPE) {
                return jsonObject.toString()
            }
        }
        return null
    }

    private fun findRecipe(jsonArray: JSONArray): String? {
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.get(i) as JSONObject
            val recipe = findRecipe(jsonObject)
            if (recipe != null) {
                return recipe
            }
        }
        return null
    }
}
