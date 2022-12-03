package com.tridiv.tridivroymisltest.data.repository

import com.tridiv.tridivroymisltest.data.model.networkPojo.TvDetails.TvDetailsResponseBody
import com.tridiv.tridivroymisltest.data.model.networkPojo.TvListItems.TvListItemsResponseBody
import com.tridiv.tridivroymisltest.data.model.networkPojo.TvListItems.TvListItemsResponseBodyItem

interface Repository {
    suspend fun getTvList(): List<TvListItemsResponseBodyItem>?
    suspend fun getTvDetails(id: Int): TvDetailsResponseBody?
}