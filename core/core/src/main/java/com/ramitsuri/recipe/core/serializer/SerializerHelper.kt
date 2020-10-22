package com.ramitsuri.recipe.core.serializer

import com.ramitsuri.recipe.core.constants.Constants

class SerializerHelper {
    private val serializer: Serializer

    init {
        val type = Constants.SerializerType.GSON
        serializer = SerializerFactory().getSerializer(type)
    }

    fun <T : Any> deserialize(json: String, classOfT: Class<T>): T? {
        return serializer.deserialize(json, classOfT)
    }

    fun <T : Any> serialize(t: T): String {
        return serializer.serialize(t)
    }
}
