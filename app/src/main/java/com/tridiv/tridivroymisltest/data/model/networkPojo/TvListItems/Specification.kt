package com.tridiv.tridivroymisltest.data.model.networkPojo.TvListItems


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Specification(
    @Json(name = "Audio")
    val audio: Audio?,
    @Json(name = "Connectivity")
    val connectivity: Connectivity?,
    @Json(name = "Dimensions (mm)")
    val dimensionsMm: DimensionsMm?,
    @Json(name = "Display")
    val display: Display?,
    @Json(name = "OS")
    val oS: OS?,
    @Json(name = "Others")
    val others: Others?,
    @Json(name = "Power")
    val power: Power?,
    @Json(name = "Product Type")
    val productType: ProductType?,
    @Json(name = "Smart Feature")
    val smartFeature: SmartFeature?,
    @Json(name = "Video")
    val video: Video?
)