package com.example.dictionaryappwithcaching.feature_dictionary.presentation

import com.example.dictionaryappwithcaching.feature_dictionary.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItem: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)
