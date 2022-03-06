package com.example.dictionaryappwithcaching.feature_dictionary.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.dictionaryappwithcaching.feature_dictionary.data.util.JsonParser
import com.example.dictionaryappwithcaching.feature_dictionary.domain.model.Meaning
import com.google.gson.reflect.TypeToken

/**
 * By default, type converters can't have constructors like we have used here.
 * So, using @ProvidedTypeConverter in order to provide our own instances of these type converters.
 * Also, later we need to specify room of these converters so that it doesn't creates one on its own.
 * */
@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromMeaningsJson(json: String): List<Meaning> {
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json,
            // Obtaining type using the type token object of custom type
            object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toMeaningsJson(meanings: List<Meaning>): String {
        return jsonParser.toJson(
            meanings,
            // Obtaining type using the type token object of custom type
            object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: "[]"
    }
}