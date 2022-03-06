package com.example.dictionaryappwithcaching.feature_dictionary.data.repository

import com.example.dictionaryappwithcaching.core.util.Resource
import com.example.dictionaryappwithcaching.feature_dictionary.data.local.WordInfoDao
import com.example.dictionaryappwithcaching.feature_dictionary.data.remote.DictionaryApi
import com.example.dictionaryappwithcaching.feature_dictionary.domain.model.WordInfo
import com.example.dictionaryappwithcaching.feature_dictionary.domain.repository.WordInfoRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepoImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
): WordInfoRepo {

    /**
     * Principle: Single source of truth
     * It means all the data must come from the same source i.e. our repository.
     * Thus, adding the caching logic here in the repository implementation.
     * */
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        // We are still loading for the data from the api but checking & providing the data
        // from local cache if it exists.
        val wordInfoList = dao.getWordInfoList(word).map { it.toWordInfo() }
        emit(Resource.Loading(wordInfoList))

        try {
            val remoteWordInfoList = api.getWordInfo(word)
            // Replacing the data in the local database with the data we get from the api.
            dao.deleteWordInfoList(remoteWordInfoList.map { it.word ?: "" })
            dao.insertWordInfoList(remoteWordInfoList.map { it.toWordInfoEntity() })
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                // We may still have the data from local cache.
                data = wordInfoList
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.",
                // We may still have the data from local cache.
                data = wordInfoList
            ))
        }

        // Sending the updated data
        val newWordInfoList = dao.getWordInfoList(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfoList))
    }
}