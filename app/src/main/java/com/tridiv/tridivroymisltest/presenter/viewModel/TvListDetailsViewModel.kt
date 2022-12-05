package com.tridiv.tridivroymisltest.presenter.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
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
class TvListDetailsViewModel @Inject constructor(
    private val repository: Repository,
    @ApplicationContext context: Context,
) : ViewModel() {


    fun getTvList(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getTvList()
                sortingDataForDaoPagination(context, response?.toMutableList() ?: mutableListOf())
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


    val pageNoLiveData = ArrayList<Int>()


    private fun sortingDataForDaoPagination(
        context: Context,
        list: MutableList<TvListItemsResponseBodyItem>
    ) {
        repository.clearDb()
        var counter = 0
        var pageNo = 1
        pageNoLiveData.add(pageNo)
        for (item in list) {
            if (counter == 5) {
                counter = 0
                pageNo++
                repository.insertTvData(
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
                repository.insertTvData(
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

}