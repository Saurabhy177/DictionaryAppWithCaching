package com.example.dictionaryappwithcaching.di

import android.app.Application
import androidx.room.Room
import com.example.dictionaryappwithcaching.feature_dictionary.data.local.Converters
import com.example.dictionaryappwithcaching.feature_dictionary.data.local.WordInfoDatabase
import com.example.dictionaryappwithcaching.feature_dictionary.data.remote.DictionaryApi
import com.example.dictionaryappwithcaching.feature_dictionary.data.repository.WordInfoRepoImpl
import com.example.dictionaryappwithcaching.feature_dictionary.data.util.GsonParser
import com.example.dictionaryappwithcaching.feature_dictionary.domain.repository.WordInfoRepo
import com.example.dictionaryappwithcaching.feature_dictionary.domain.usecases.GetWordInfoUseCase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * The "InstallIn" annotation specifies for how long the injected dependencies will live.
 * Here, "SingletonComponent" class means all the dependencies live as long as our application.
 * */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * The "Singleton" annotation ensures there is only single instance throughout the whole
     * lifetime of our app.
     * */
    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repo: WordInfoRepo) = GetWordInfoUseCase(repo)

    @Provides
    @Singleton
    fun provideWordInfoRepo(
        api: DictionaryApi,
        db: WordInfoDatabase
    ): WordInfoRepo {
        return WordInfoRepoImpl(api, db.wordInfoDao)
    }

    @Provides
    @Singleton
    fun provideWordInfoDB(app: Application) = Room
        .databaseBuilder(
            app,
            WordInfoDatabase::class.java,
            WordInfoDatabase.DATABASE_NAME
        )
        .addTypeConverter(Converters(GsonParser(Gson())))
        .build()

    @Provides
    @Singleton
    fun provideDictionaryApi() = Retrofit.Builder()
        .baseUrl(DictionaryApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DictionaryApi::class.java)
}