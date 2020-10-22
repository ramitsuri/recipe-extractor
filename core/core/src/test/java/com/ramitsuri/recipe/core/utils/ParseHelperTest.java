package com.ramitsuri.recipe.core.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

class ParseHelperTest {
    ParseHelper parseHelper;

    @Before
    void setUp() {
        parseHelper = new ParseHelper();
    }

    @Test
    void fromJsonYields() {
        String yields = "Serves 4.0";
        assertEquals(4.0, parseHelper.fromJsonYields(yields), 0.0);

        yields = "Serves 4";
        assertEquals(4.0, parseHelper.fromJsonYields(yields), 0.0);

        yields = "Serves 4.0.";
        assertEquals(4.0, parseHelper.fromJsonYields(yields), 0.0);

        yields = "Serves 4.0dks";
        assertEquals(4.0, parseHelper.fromJsonYields(yields), 0.0);

        yields = " 4.0";
        assertEquals(4.0, parseHelper.fromJsonYields(yields), 0.0);

        yields = " 4.0 ";
        assertEquals(4.0, parseHelper.fromJsonYields(yields), 0.0);

        yields = "4.";
        assertEquals(4.0, parseHelper.fromJsonYields(yields), 0.0);

        yields = "Serves";
        assertEquals(0.0, parseHelper.fromJsonYields(yields), 0.0);
    }
}