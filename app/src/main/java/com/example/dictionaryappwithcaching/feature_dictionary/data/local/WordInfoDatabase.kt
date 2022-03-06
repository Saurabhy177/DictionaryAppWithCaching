package com.example.dictionaryappwithcaching.feature_dictionary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dictionaryappwithcaching.feature_dictionary.data.local.entity.WordInfoEntity

@Database(
    entities = [WordInfoEntity::class],
    version = 1
)
// Specifying our own converters for the custom types like Meaning.
@TypeConverters(Converters::class)
abstract class WordInfoDatabase: RoomDatabase() {

    abstract val wordInfoDao: WordInfoDao

    companion object {
        const val DATABASE_NAME = "word_db"
    }
}