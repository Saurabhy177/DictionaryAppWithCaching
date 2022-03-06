package com.example.dictionaryappwithcaching.feature_dictionary.domain.model

import androidx.annotation.Keep

@Keep
data class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String
)