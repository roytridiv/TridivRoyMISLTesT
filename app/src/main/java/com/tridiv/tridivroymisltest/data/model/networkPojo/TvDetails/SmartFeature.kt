package com.tridiv.tridivroymisltest.data.model.networkPojo.TvDetails


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SmartFeature(
    @Json(name = "Mobile to TV - Mirroring, DLNA")
    val mobileToTVMirroringDLNA: String?,
    @Json(name = "Sound Mirroring")
    val soundMirroring: String?,
    @Json(name = "TV Sound to Mobile")
    val tVSoundToMobile: String?
)