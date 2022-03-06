package com.example.dictionaryappwithcaching.feature_dictionary.data.remote

import com.example.dictionaryappwithcaching.feature_dictionary.data.remote.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Api url: https://api.dictionaryapi.dev/api/v2/entries/en/bank
 *
 * For Get requests:
 * @Path is used for adding a path in url concatenated by forward slash
 * @Query is used for adding variables in url concatenated by '?' and '&'
 * For Post requests:
 * @Body is used for sending a bunch of values
 * */
interface DictionaryApi {

    companion object {
        const val BASE_URL = "https://api.dictionaryapi.dev/"
    }

    @GET("api/v2/entries/en/{word}")
    suspend fun getWordInfo(
        @Path ("word") word: String
    ): List<WordInfoDto>
}