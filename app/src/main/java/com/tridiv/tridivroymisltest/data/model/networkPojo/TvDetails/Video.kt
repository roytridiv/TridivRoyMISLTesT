package com.tridiv.tridivroymisltest.data.model.networkPojo.TvDetails


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Video(
    @Json(name = "Contrast")
    val contrast: String?,
    @Json(name = "Contrast Enhancer")
    val contrastEnhancer: String?,
    @Json(name = "HDR 10+")
    val hDR10: String?,
    @Json(name = "HDR (High Dynamic Range)")
    val hDRHighDynamicRange: String?,
    @Json(name = "PQI (Picture Quality Index)")
    val pQIPictureQualityIndex: String?
)