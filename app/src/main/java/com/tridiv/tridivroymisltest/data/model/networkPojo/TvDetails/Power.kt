package com.tridiv.tridivroymisltest.data.model.networkPojo.TvDetails


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Power(
    @Json(name = "Power Consumption (Max)")
    val powerConsumptionMax: String?
)