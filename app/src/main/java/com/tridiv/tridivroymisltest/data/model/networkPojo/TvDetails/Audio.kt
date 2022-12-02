package com.tridiv.tridivroymisltest.data.model.networkPojo.TvDetails


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Audio(
    @Json(name = "Dolby Digital Plus")
    val dolbyDigitalPlus: String?,
    @Json(name = "Sound Output (RMS)")
    val soundOutputRMS: String?,
    @Json(name = "Speaker Type")
    val speakerType: String?
)