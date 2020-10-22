package com.ramitsuri.recipe.core.serializer

import com.ramitsuri.recipe.core.constants.Constants
import com.ramitsuri.recipe.core.model.interfaces.Author
import com.ramitsuri.recipe.core.model.interfaces.Instruction
import com.ramitsuri.recipe.core.model.interfaces.Keywords
import com.ramitsuri.recipe.core.model.interfaces.Yield
import com.ramitsuri.recipe.core.model.json.*
import com.squareup.moshi.*
import java.time.Duration
import java.time.Instant

/**
 * Resources for research
 * https://github.com/square/moshi
 * https://proandroiddev.com/moshi-polymorphic-adapter-is-d25deebbd7c5
 * https://stackoverflow.com/questions/49165604/moshi-determine-if-json-is-array-or-single-object/49204281#49204281
 * https://stackoverflow.com/questions/40950240/how-to-deserialize-generic-types-with-moshi
 */
class MoshiHelper : Serializer {

    override fun <T : Any> deserialize(json: String, classOfT: Class<T>): T? {
        val adapter = getMoshi().adapter(classOfT)
        try {
            return adapter.fromJson(json)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    override fun <T : Any> serialize(t: T): String {
        throw Exception()
    }

    private fun getMoshi(): Moshi {
        return Moshi.Builder()
                .add(DateAdapter)
                .add(DurationAdapter)
                .add(Yield::class.java, yieldJsonAdapter)
                .add(Keywords::class.java, keywordsJsonAdapter)
                .add(Author::class.java, authorJsonAdapter)
                .add(Instruction::class.java, instructionJsonAdapter)
                .build()
    }


    private object DateAdapter {
        @FromJson
        fun fromJson(json: String): Instant {
            return Instant.parse(json)
        }

        @ToJson
        fun toJson(instant: Instant): String {
            return instant.toString()
        }
    }

    private object DurationAdapter {
        @FromJson
        fun fromJson(json: String): Duration {
            return Duration.parse(json)
        }

        @ToJson
        fun toJson(duration: Duration): String {
            return duration.toString()
        }
    }


    private val yieldJsonAdapter = object : JsonAdapter<Yield>() {
        override fun fromJson(jsonReader: JsonReader): Yield {
            return JsonYield()
        }

        override fun toJson(jsonWriter: JsonWriter, yield: Yield?) {
        }
    }

    private val keywordsJsonAdapter = object : JsonAdapter<Keywords>() {
        override fun fromJson(jsonReader: JsonReader): Keywords {
            return JsonKeywords()
        }

        override fun toJson(jsonWriter: JsonWriter, keywords: Keywords?) {
        }
    }

    private val authorJsonAdapter = object : JsonAdapter<Author>() {
        override fun fromJson(jsonReader: JsonReader): JsonAuthor {
            return JsonAuthor()
        }

        override fun toJson(jsonWriter: JsonWriter, author: Author?) {
        }
    }

    private val instructionJsonAdapter = object : JsonAdapter<Instruction>() {
        override fun fromJson(jsonReader: JsonReader): Instruction? {
            try {
                if (jsonReader.peek() == JsonReader.Token.BEGIN_OBJECT) {
                    val map = jsonReader.readJsonValue() as Map<*, *>
                    val type = map[Constants.JsonKeys.TYPE]
                    if (type != null) {
                        if (type.toString() == Constants.JsonKeys.INSTRUCTION_SECTION) {
                            val adapter = getMoshi().adapter(JsonInstructionSection::class.java)
                            return adapter.fromJson(jsonReader)
                        } else if (type.toString() == Constants.JsonKeys.INSTRUCTION_STEP) {
                            val adapter = getMoshi().adapter(JsonInstructionStep::class.java)
                            return adapter.fromJson(jsonReader)
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        override fun toJson(jsonWriter: JsonWriter, instruction: Instruction?) {
        }
    }
}
