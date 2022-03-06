package com.example.dictionaryappwithcaching.feature_dictionary.data.remote.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class PhoneticDto(
    @SerializedName("audio")
    val audio: String?,
    @SerializedName("sourceUrl")
    val sourceUrl: String?,
    @SerializedName("text")
    val text: String?
)