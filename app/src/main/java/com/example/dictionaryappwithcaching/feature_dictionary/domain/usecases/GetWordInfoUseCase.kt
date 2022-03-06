package com.example.dictionaryappwithcaching.feature_dictionary.domain.usecases

import com.example.dictionaryappwithcaching.core.util.Resource
import com.example.dictionaryappwithcaching.feature_dictionary.domain.model.WordInfo
import com.example.dictionaryappwithcaching.feature_dictionary.domain.repository.WordInfoRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfoUseCase(
    private val repo: WordInfoRepo
) {

    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()) {
            return flow {  }
        }
        return repo.getWordInfo(word)
    }
}