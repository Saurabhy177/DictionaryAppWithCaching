package com.example.dictionaryappwithcaching.feature_dictionary.data.remote.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.example.dictionaryappwithcaching.feature_dictionary.domain.model.Meaning

@Keep
data class MeaningDto(
    @SerializedName("definitions")
    val definitions: List<DefinitionDto>?,
    @SerializedName("partOfSpeech")
    val partOfSpeech: String?
) {
    fun toMeaning(): Meaning {
        return Meaning(
            definitions = definitions?.map {
                it.toDefinition()
            } ?: emptyList(),
            partOfSpeech = partOfSpeech ?: ""
        )
    }
}