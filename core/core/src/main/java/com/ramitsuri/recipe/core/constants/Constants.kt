package com.ramitsuri.recipe.core.constants

object Constants {
    object JsonKeys {
        const val TYPE = "@type"
        const val RECIPE = "Recipe"
        const val INSTRUCTION_STEP = "HowToStep"
        const val INSTRUCTION_SECTION = "HowToSection"
    }

    object HtmlKeys {
        const val TYPE = "type"
        const val JSON_LD = "application/ld+json"
    }

    object SerializerType {
        const val MOSHI = 0
        const val GSON = 1
    }
}
