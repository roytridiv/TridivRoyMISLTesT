package com.tridiv.tridivroymisltest.data.model.networkPojo.TvListItems


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DimensionsMm(
    @Json(name = "Set Size with Stand (WxHxD)")
    val setSizeWithStandWxHxD: String?,
    @Json(name = "Set Size without Stand (WxHxD)")
    val setSizeWithoutStandWxHxD: String?
)