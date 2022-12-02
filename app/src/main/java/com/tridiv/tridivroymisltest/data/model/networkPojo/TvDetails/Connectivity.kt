package com.tridiv.tridivroymisltest.data.model.networkPojo.TvDetails


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Connectivity(
    @Json(name = "HDMI")
    val hDMI: String?,
    @Json(name = "USB")
    val uSB: String?
)