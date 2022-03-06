package com.example.dictionaryappwithcaching.feature_dictionary.data.remote.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.example.dictionaryappwithcaching.feature_dictionary.domain.model.Definition

@Keep
data class DefinitionDto(
    @SerializedName("antonyms")
    val antonyms: List<String>?,
    @SerializedName("definition")
    val definition: String?,
    @SerializedName("synonyms")
    val synonyms: List<String>?,
    @SerializedName("example")
    val example: String?
) {
    fun toDefinition(): Definition {
        return Definition(
            antonyms = antonyms ?: emptyList(),
            definition = definition ?: "",
            synonyms = synonyms ?: emptyList(),
            example = example ?: ""
        )
    }
}