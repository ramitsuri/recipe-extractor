package com.ramitsuri.recipe.app.service

import com.ramitsuri.recipe.core.RecipeExtractor
import org.springframework.stereotype.Service

@Service
class RecipeService {
    private lateinit var sUrl: String

    fun getRecipe(url: String): String? {
        val extractor = RecipeExtractor()
        return extractor.getJson(url)
    }

    fun getStatic(): String? {
        return getRecipe(sUrl)
    }

    fun setStatic(url: String) {
        sUrl = url
    }
}