package com.tridiv.tridivroymisltest.data.model.networkPojo.TvDetails


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Others(
    @Json(name = "Bezel Type")
    val bezelType: String?,
    @Json(name = "Design")
    val design: String?,
    @Json(name = "Micro Dimming")
    val microDimming: String?,
    @Json(name = "Picture Engine")
    val pictureEngine: String?,
    @Json(name = "Power Supply")
    val powerSupply: String?,
    @Json(name = "Q-Symphony")
    val qSymphony: String?,
    @Json(name = "Series")
    val series: String?,
    @Json(name = "TV to Mobile - Mirroring")
    val tVToMobileMirroring: String?,
    @Json(name = "Web Browser")
    val webBrowser: String?
)