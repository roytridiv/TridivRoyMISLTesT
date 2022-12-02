package com.tridiv.tridivroymisltest.data.model.networkPojo.TvListItems


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Others(
    @Json(name = "Auto Motion Plus")
    val autoMotionPlus: String?,
    @Json(name = "Dolby Atmos")
    val dolbyAtmos: String?,
    @Json(name = "Film Mode")
    val filmMode: String?,
    @Json(name = "Micro Dimming")
    val microDimming: String?,
    @Json(name = "Power Supply")
    val powerSupply: String?,
    @Json(name = "Series")
    val series: String?,
    @Json(name = "TV to Mobile - Mirroring")
    val tVToMobileMirroring: String?,
    @Json(name = "Web Browser")
    val webBrowser: String?
)