package com.ramitsuri.recipe.core.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test


class ParseHelperTest {
    lateinit var parseHelper:ParseHelper

    @BeforeAll
    fun setUp() {
        parseHelper = ParseHelper()
    }

    @Test
    fun fromJsonYields() {
        var yields = "Serves 4.0"
        assertEquals(4.0, parseHelper.fromJsonYields(yields), 0.0)

        yields = "Serves 4"
        assertEquals(4.0, parseHelper.fromJsonYields(yields), 0.0)

        yields = "Serves 4.0."
        assertEquals(4.0, parseHelper.fromJsonYields(yields), 0.0)

        yields = "Serves 4.0dks"
        assertEquals(4.0, parseHelper.fromJsonYields(yields), 0.0)

        yields = " 4.0"
        assertEquals(4.0, parseHelper.fromJsonYields(yields), 0.0)

        yields = " 4.0 "
        assertEquals(4.0, parseHelper.fromJsonYields(yields), 0.0)

        yields = "4."
        assertEquals(4.0, parseHelper.fromJsonYields(yields), 0.0)

        yields = "Serves"
        assertEquals(0.0, parseHelper.fromJsonYields(yields), 0.0)
    }
}