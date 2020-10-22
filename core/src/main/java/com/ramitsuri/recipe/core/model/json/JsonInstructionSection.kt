package com.ramitsuri.recipe.core.model.json

import com.google.gson.annotations.SerializedName
import com.ramitsuri.recipe.core.model.interfaces.Instruction

class JsonInstructionSection : Instruction {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("itemListElement")
    var instructions: List<Instruction> = emptyList()
}
