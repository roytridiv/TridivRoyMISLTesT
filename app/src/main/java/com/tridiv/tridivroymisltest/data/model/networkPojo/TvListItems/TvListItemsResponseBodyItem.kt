package com.tridiv.tridivroymisltest.data.model.networkPojo.TvListItems


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class TvListItemsResponseBodyItem(
    @Json(name = "description")
    val description: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "image_url")
    val imageUrl: String?,
    @Json(name = "model")
    val model: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "price")
    val price: Int?,
    @Json(name = "size")
    val size: Int?,
    @Json(name = "specification")
    val specification: Specification?,
    @Json(name = "title")
    val title: String?
)