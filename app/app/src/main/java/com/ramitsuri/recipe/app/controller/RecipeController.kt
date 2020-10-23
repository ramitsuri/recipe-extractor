package com.ramitsuri.recipe.app.controller

import com.ramitsuri.recipe.app.service.RecipeService
import com.ramitsuri.recipe.core.utils.Log
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@RestController
@RequestMapping("/recipe")
class RecipeController(private val recipeService: RecipeService) {
    private val TAG = "RecipeController"

    // @CrossOrigin(origins = "http://10.48.69.70:8080")
    @RequestMapping("/extract")
    fun extract(@RequestParam(value = "recipe") url: String): String? {
        val request = (RequestContextHolder.currentRequestAttributes()
                as ServletRequestAttributes).request
        logRequest(request.remoteAddr, url)
        val recipeJson = recipeService.getRecipe(url)
        logResponse(request.remoteAddr, url)
        return recipeJson
    }

    @RequestMapping("/get")
    fun get(): String? {
        return recipeService.getStatic()
    }

    @RequestMapping("/set")
    fun set(@RequestParam(value = "recipe") url: String) {
        recipeService.setStatic(url)
    }

    private fun logRequest(remote: String, requestUrl: String) {
        Log.d(TAG, """
            
            -----------------------BEGIN-----------------------
            Request received 
            From $remote
            For $requestUrl
            ------------------------END------------------------
            
            """.trimIndent())
    }

    private fun logResponse(remote: String, requestUrl: String) {
        Log.d(TAG, """
            
            -----------------------BEGIN-----------------------
            Response sent 
            To $remote
            For $requestUrl
            ------------------------END------------------------
            
            """.trimIndent())
    }
}
