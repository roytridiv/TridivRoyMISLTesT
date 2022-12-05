package com.tridiv.tridivroymisltest.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.tridiv.tridivroymisltest.data.db.AppDatabase
import com.tridiv.tridivroymisltest.data.db.TvDataDao
import com.tridiv.tridivroymisltest.data.model.TvDaoItem
import com.tridiv.tridivroymisltest.data.model.networkPojo.TvDetails.TvDetailsResponseBody
import com.tridiv.tridivroymisltest.data.model.networkPojo.TvDetailsReqBody
import com.tridiv.tridivroymisltest.data.model.networkPojo.TvListItems.TvListItemsResponseBody
import com.tridiv.tridivroymisltest.data.model.networkPojo.TvListItems.TvListItemsResponseBodyItem
import com.tridiv.tridivroymisltest.data.network.RestApiService
import com.tridiv.tridivroymisltest.data.repository.Repository

class RepositoryImplementation(
    private val apiService: RestApiService,
    private val tvDataDao: TvDataDao,
    val context: Context
) : Repository {

    override suspend fun getTvList(): List<TvListItemsResponseBodyItem>? {
        return try {
            apiService.getTvListItems().body()
        } catch (e: Exception) {
            Log.e("REPO_ERROR", e.message ?: "")
            null
        }
    }

    override suspend fun getTvDetails(id: Int): TvDetailsResponseBody? {
        return try {
            apiService.getTvDetails(TvDetailsReqBody(id)).body()
        } catch (e: Exception) {
            Log.e("REPO_ERROR", e.message ?: "")
            null
        }
    }

    override fun getAllTvDataFromCurrentPage(page: String): LiveData<List<TvDaoItem>> {
        return tvDataDao.getAllTelevisionLiveData(page)
    }

    override fun insertTvData(item: TvDaoItem) {
        tvDataDao.addTelevisionData(item)
    }

    override fun clearDb(){
        tvDataDao.clearTable()
    }
}