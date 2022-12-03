package com.tridiv.tridivroymisltest.data.model.networkPojo.TvListItems

import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
class TvListItemsResponseBody : ArrayList<TvListItemsResponseBodyItem>()