package com.example.dictionaryappwithcaching.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dictionaryappwithcaching.feature_dictionary.domain.model.Meaning
import com.example.dictionaryappwithcaching.feature_dictionary.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    // Room doesn't understand the type Meaning. So, we can either:
    // a) Create a new entity Meaning or
    // b) Create a type converter for Meaning to string conversion.
    val meanings: List<Meaning>,
    val origin: String,
    val phonetic: String,
    val word: String,
    @PrimaryKey val id: Int? = null
) {
    fun toWordInfo(): WordInfo {
        return WordInfo(
            meanings = meanings,
            origin = origin,
            phonetic = phonetic,
            word = word
        )
    }
}
