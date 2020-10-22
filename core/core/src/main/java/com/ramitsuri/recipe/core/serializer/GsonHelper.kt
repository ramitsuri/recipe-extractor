package com.ramitsuri.recipe.core.serializer

import com.google.gson.*
import com.ramitsuri.recipe.core.constants.Constants
import com.ramitsuri.recipe.core.model.interfaces.Author
import com.ramitsuri.recipe.core.model.interfaces.Instruction
import com.ramitsuri.recipe.core.model.interfaces.Keywords
import com.ramitsuri.recipe.core.model.interfaces.Yield
import com.ramitsuri.recipe.core.model.json.*
import java.lang.reflect.Type
import java.time.Duration
import java.time.Instant
import java.time.format.DateTimeFormatter

class GsonHelper : Serializer {

    override fun <T : Any> deserialize(json: String, classOfT: Class<T>): T? {
        return getBuilder().create().fromJson(json, classOfT)
    }

    override fun <T : Any> serialize(t: T): String {
        return getBuilder().create().toJson(t)
    }

    private fun getBuilder(): GsonBuilder {
        val builder = GsonBuilder()
                .disableHtmlEscaping()

        builder.registerTypeAdapter(Yield::class.java, getYieldAdapter())
                .registerTypeAdapter(Keywords::class.java, getKeywordsAdapter())
                .registerTypeAdapter(Author::class.java, getAuthorAdapter())
                .registerTypeAdapter(Instruction::class.java, getInstructionAdapter())
                .registerTypeAdapter(Instant::class.java, InstantAdapter)
                .registerTypeAdapter(Duration::class.java, DurationAdapter)
        return builder
    }


    private fun getYieldAdapter(): JsonDeserializer<Yield>? {
        return JsonDeserializer<Yield> { json, typeOfT, context ->
            try {
                if (json is JsonArray) {
                    return@JsonDeserializer JsonYield().fromString(json.get(0).asString)
                } else {
                    return@JsonDeserializer JsonYield().fromString(json.asString)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return@JsonDeserializer null
        }
    }

    private fun getKeywordsAdapter(): JsonDeserializer<Keywords>? {
        return JsonDeserializer<Keywords> { json, typeOfT, context ->
            try {
                if (json is JsonArray) {
                    val words = mutableListOf<String>()
                    for (element in json) {
                        words.add(element.asString)
                    }
                    return@JsonDeserializer JsonKeywords().fromStringArray(words)
                } else {
                    return@JsonDeserializer JsonKeywords().fromString(json.asString)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return@JsonDeserializer null
        }
    }


    private fun getAuthorAdapter(): JsonDeserializer<Author>? {
        return JsonDeserializer<Author> { json, typeOfT, context ->
            try {
                var jsonToDeserialize = json
                if (json is JsonArray) {
                    if (json.size() > 0) {
                        jsonToDeserialize = json.get(0)
                    }
                }
                return@JsonDeserializer context.deserialize(jsonToDeserialize, JsonAuthor::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return@JsonDeserializer null
        }
    }

    private fun getInstructionAdapter(): JsonDeserializer<Instruction>? {
        return JsonDeserializer<Instruction> { json, typeOfT, context ->
            try {
                if (json is JsonObject) {
                    val type = json.get(Constants.JsonKeys.TYPE)
                    if (type != null) {
                        val typeVal = type.asString
                        if (typeVal == Constants.JsonKeys.INSTRUCTION_SECTION) {
                            return@JsonDeserializer context.deserialize(json, JsonInstructionSection::class.java)
                        } else if (typeVal == Constants.JsonKeys.INSTRUCTION_STEP) {
                            return@JsonDeserializer context.deserialize(json, JsonInstructionStep::class.java)
                        }
                    }
                } else {
                    val instructionStep = JsonInstructionStep()
                    instructionStep.text = json.asString
                    return@JsonDeserializer instructionStep
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return@JsonDeserializer null
        }
    }

    private object InstantAdapter : JsonSerializer<Instant>,
            JsonDeserializer<Instant> {
        private val FORMATTER = DateTimeFormatter.ISO_INSTANT

        override fun deserialize(json: JsonElement, typeOfT: Type,
                                 context: JsonDeserializationContext): Instant {
            return FORMATTER.parse(json.asString, Instant::from)
        }

        override fun serialize(src: Instant, typeOfSrc: Type,
                               context: JsonSerializationContext): JsonElement {
            return JsonPrimitive(FORMATTER.format(src))
        }
    }

    private object DurationAdapter : JsonSerializer<Duration>,
            JsonDeserializer<Duration> {

        override fun deserialize(json: JsonElement, typeOfT: Type,
                                 context: JsonDeserializationContext): Duration {
            return Duration.parse(json.asString)
        }

        override fun serialize(src: Duration, typeOfSrc: Type,
                               context: JsonSerializationContext): JsonElement {
            return JsonPrimitive(src.toString())
        }
    }
}
