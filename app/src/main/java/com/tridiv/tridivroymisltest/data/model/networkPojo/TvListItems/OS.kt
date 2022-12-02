package com.tridiv.tridivroymisltest.data.model.networkPojo.TvListItems


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OS(
    @Json(name = "Operating System")
    val operatingSystem: String?
)