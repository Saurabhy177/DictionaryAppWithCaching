package com.example.dictionaryappwithcaching.feature_dictionary.domain.repository

import com.example.dictionaryappwithcaching.core.util.Resource
import com.example.dictionaryappwithcaching.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepo {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}