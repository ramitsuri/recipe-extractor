package com.ramitsuri.recipe.app.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.time.Instant

@RestController
@RequestMapping("/test")
class TestController {

    @RequestMapping("/alive")
    fun alive(): String {
        return Instant.now().toString()
    }
}
