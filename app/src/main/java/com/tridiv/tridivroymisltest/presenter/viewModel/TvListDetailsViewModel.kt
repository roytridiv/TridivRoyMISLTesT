package com.tridiv.tridivroymisltest.presenter.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tridiv.tridivroymisltest.data.RepositoryImplementation
import com.tridiv.tridivroymisltest.data.db.AppDatabase
import com.tridiv.tridivroymisltest.data.model.TvDaoItem
import com.tridiv.tridivroymisltest.data.model.networkPojo.TvDetails.TvDetailsResponseBody
import com.tridiv.tridivroymisltest.data.model.networkPojo.TvListItems.TvListItemsResponseBodyItem
import com.tridiv.tridivroymisltest.data.network.NetworkProvider
import com.tridiv.tridivroymisltest.data.repository.Repository
import com.tridiv.tridivroymisltest.presenter.model.TvData
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TvListDetailsViewModel@Inject constructor(
    private val repository: Repository,
    @ApplicationContext context: Context,
) : ViewModel() {

//    private val restApiProvider = NetworkProvider()
//    private val repository: RepositoryImplementation =
//        RepositoryImplementation(restApiProvider.test())

    var tvListDataResponse = MutableLiveData<MutableList<TvListItemsResponseBodyItem>>()
    var bkashShowSavedAccountError = MutableLiveData<String>()

    fun getTvList(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getTvList()
                sortingDataForDaoPagination(context, response?.toMutableList() ?: mutableListOf())
//                tvListDataResponse.postValue(response?.toMutableList())

            } catch (e: Exception) {

            }
        }
    }

    var tvDetailsDataResponse = MutableLiveData<TvDetailsResponseBody?>()
    var apiLoading: MutableLiveData<Boolean> = MutableLiveData()
    fun getTvDetails(id: Int) {
        apiLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getTvDetails(id)
                tvDetailsDataResponse.postValue(response)
                apiLoading.postValue(true)

            } catch (e: Exception) {
                apiLoading.postValue(true)
            }
            apiLoading.postValue(true)
        }
    }

    //    val pageNoLiveData = MutableLiveData<Int>()
    val pageNoLiveData = ArrayList<Int>()


    private fun sortingDataForDaoPagination(
        context: Context,
        list: MutableList<TvListItemsResponseBodyItem>
    ) {
        val appData = AppDatabase.getDB(context).getTvDao()
        appData.clearTable()
        var counter = 0
        var pageNo = 1
        pageNoLiveData.add(pageNo)
        for (item in list) {
            if (counter == 5) {
                counter = 0
                pageNo++
                appData.addTelevisionData(
                    TvDaoItem(
                        pageNo.toString(),
                        item.id ?: -1,
                        item.imageUrl ?: "",
                        item.name ?: "",
                        item.model ?: "",
                        item.size.toString(),
                        item.price.toString()
                    )
                )
                pageNoLiveData.add(pageNo)
            } else {
                appData.addTelevisionData(
                    TvDaoItem(
                        pageNo.toString(),
                        item.id ?: -1,
                        item.imageUrl ?: "",
                        item.name ?: "",
                        item.model ?: "",
                        item.size.toString(),
                        item.price.toString()
                    )
                )
                counter++
            }

        }
    }

    fun clearTable(context: Context) {
        val appData = AppDatabase.getDB(context).getTvDao()
        appData.clearTable()
    }



    fun createDummyTvList(): MutableList<TvData> {
        return mutableListOf(
            TvData(
                1,
                "https://transcomdigital.com/_next/image?url=https%3A%2F%2Ftranscom-storage.s3.amazonaws.com%2F2eb459e3-036e-4c2f-ac2a-2a2039c4a351%2Fddd04b35-531a-4fe0-bc10-d4103d6bf2a6%2Fimages%2Fe60a0107-2116-486f-a7ed-551b045f7b8a&w=1500&q=100",
                "sony", "asdfgj", "44", "98765"
            ),
            TvData(
                2,
                "https://transcomdigital.com/_next/image?url=https%3A%2F%2Ftranscom-storage.s3.amazonaws.com%2F2eb459e3-036e-4c2f-ac2a-2a2039c4a351%2Fddd04b35-531a-4fe0-bc10-d4103d6bf2a6%2Fimages%2Fe60a0107-2116-486f-a7ed-551b045f7b8a&w=1500&q=100",
                "LG", "asdfgj", "44", "98765"
            ),
            TvData(
                3,
                "https://transcomdigital.com/_next/image?url=https%3A%2F%2Ftranscom-storage.s3.amazonaws.com%2F2eb459e3-036e-4c2f-ac2a-2a2039c4a351%2Fddd04b35-531a-4fe0-bc10-d4103d6bf2a6%2Fimages%2Fe60a0107-2116-486f-a7ed-551b045f7b8a&w=1500&q=100",
                "walton", "asdfgj", "44", "98765"
            ),
            TvData(
                4,
                "https://transcomdigital.com/_next/image?url=https%3A%2F%2Ftranscom-storage.s3.amazonaws.com%2F2eb459e3-036e-4c2f-ac2a-2a2039c4a351%2Fddd04b35-531a-4fe0-bc10-d4103d6bf2a6%2Fimages%2Fe60a0107-2116-486f-a7ed-551b045f7b8a&w=1500&q=100",
                "toshiba", "asdfgj", "44", "98765"
            ),
        )
    }
}