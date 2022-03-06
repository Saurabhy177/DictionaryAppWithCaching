package com.example.dictionaryappwithcaching.feature_dictionary.data.remote.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.example.dictionaryappwithcaching.feature_dictionary.data.local.entity.WordInfoEntity

@Keep
data class WordInfoDto(
    @SerializedName("meanings")
    val meanings: List<MeaningDto>?,
    @SerializedName("origin")
    val origin: String?,
    @SerializedName("phonetic")
    val phonetic: String?,
    @SerializedName("phonetics")
    val phonetics: List<PhoneticDto>?,
    @SerializedName("word")
    val word: String?
) {
    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity(
            meanings = meanings?.map {
                it.toMeaning()
            } ?: emptyList(),
            origin = origin ?: "",
            phonetic = phonetic ?: "",
            word = word ?: ""
        )
    }
}