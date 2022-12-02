package com.tridiv.tridivroymisltest.data.model.networkPojo.TvDetails


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Display(
    @Json(name = "Resolution")
    val resolution: String?,
    @Json(name = "Screen Size")
    val screenSize: String?
)