package com.tridiv.tridivroymisltest.data.repository

import androidx.lifecycle.LiveData
import com.tridiv.tridivroymisltest.data.model.TvDaoItem
import com.tridiv.tridivroymisltest.data.model.networkPojo.TvDetails.TvDetailsResponseBody
import com.tridiv.tridivroymisltest.data.model.networkPojo.TvListItems.TvListItemsResponseBody
import com.tridiv.tridivroymisltest.data.model.networkPojo.TvListItems.TvListItemsResponseBodyItem

interface Repository {
    suspend fun getTvList(): List<TvListItemsResponseBodyItem>?
    suspend fun getTvDetails(id: Int): TvDetailsResponseBody?
    fun getAllTvDataFromCurrentPage(page: String): LiveData<List<TvDaoItem>>
    fun insertTvData(item: TvDaoItem)
    fun clearDb()
}