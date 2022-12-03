package com.tridiv.tridivroymisltest.data.model.networkPojo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TvDetailsReqBody(
    @Json(name = "id")
    val id: Int?
)