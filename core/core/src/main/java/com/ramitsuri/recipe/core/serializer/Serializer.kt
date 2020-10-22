package com.ramitsuri.recipe.core.serializer

interface Serializer {
    fun <T : Any> deserialize(json: String, classOfT: Class<T>): T?

    fun <T : Any> serialize(t: T): String
}
