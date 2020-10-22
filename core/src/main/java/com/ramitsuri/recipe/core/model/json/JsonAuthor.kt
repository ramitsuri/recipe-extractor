package com.ramitsuri.recipe.core.model.json

import com.google.gson.annotations.SerializedName
import com.ramitsuri.recipe.core.model.interfaces.Author

class JsonAuthor : Author {
    @SerializedName("name")
    var name: String? = null
}
