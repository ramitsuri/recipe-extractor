package com.ramitsuri.recipe.core.constants;


import org.jetbrains.annotations.Nullable;

public enum QuantityUnit {
    TEASPOON("tsp", new String[]{"tsp", "teaspoon", "teaspoons"}),
    TABLESPOON("tbsp", new String[]{"tbsp", "tablespoon", "tablespoons"}),
    CUP("cup", new String[]{"cup", "cups"}),
    NUMBER("", new String[]{" "});

    String primaryName;
    String[] otherNames;

    QuantityUnit(String primaryName, String[] otherNames) {
        this.primaryName = primaryName;
        this.otherNames = otherNames;
    }

    @Nullable
    public QuantityUnit fromName(@Nullable String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        for (QuantityUnit unit : values()) {
            if (unit.primaryName.equalsIgnoreCase(name)) {
                return unit;
            } else {
                for (String otherName : unit.otherNames) {
                    if (otherName.equalsIgnoreCase(name)) {
                        return unit;
                    }
                }
            }
        }
        return null;
    }
}
