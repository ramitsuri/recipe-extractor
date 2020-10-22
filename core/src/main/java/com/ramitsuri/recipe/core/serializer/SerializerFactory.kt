package com.ramitsuri.recipe.core.serializer

import com.ramitsuri.recipe.core.constants.Constants

class SerializerFactory {

    fun getSerializer(type: Int): Serializer {
        if (type == Constants.SerializerType.MOSHI) {
            return MoshiHelper()
        }
        return GsonHelper()
    }
}
