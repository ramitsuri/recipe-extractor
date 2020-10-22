package com.ramitsuri.recipe.core.model.json

import com.google.gson.annotations.SerializedName
import com.ramitsuri.recipe.core.model.interfaces.Instruction

class JsonInstructionStep : Instruction {

    @SerializedName("text")
    var text: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("image")
    var image: String? = null
}
