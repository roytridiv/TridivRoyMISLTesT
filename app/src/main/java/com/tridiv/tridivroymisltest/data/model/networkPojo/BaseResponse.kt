package com.tridiv.tridivroymisltest.data.model.networkPojo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class BaseResponse {
    var httpStatusCode: Int = -1

    @Json(name = "message")
    var message: String? = null
}
