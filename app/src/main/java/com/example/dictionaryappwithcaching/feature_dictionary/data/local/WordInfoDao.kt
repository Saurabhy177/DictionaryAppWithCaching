package com.example.dictionaryappwithcaching.feature_dictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dictionaryappwithcaching.feature_dictionary.data.local.entity.WordInfoEntity

@Dao
interface WordInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfoList(wordInfoList: List<WordInfoEntity>)

    @Query("DELETE FROM wordinfoentity WHERE word IN(:words)")
    suspend fun deleteWordInfoList(words: List<String>)

    // Using || to concatenate words or some strings
    @Query("SELECT * FROM wordinfoentity WHERE word like '%' || :word || '%'")
    suspend fun getWordInfoList(word: String): List<WordInfoEntity>
}