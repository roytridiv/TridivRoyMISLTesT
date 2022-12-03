package com.tridiv.tridivroymisltest.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tridiv.tridivroymisltest.data.model.TvDaoItem

@Dao
interface TvDataDao {
    @Query("select * from TvDaoItem where page = :pageNo")
    fun getAllTelevisionLiveData(pageNo: String): LiveData<TvDaoItem>

    @Query("select * from TvDaoItem")
    fun getAllTelevisionData(): List<TvDaoItem>

    @Insert
    fun addTelevisionData(tvData: TvDaoItem)


    @Query("DELETE FROM TvDaoItem")
    fun clearTable()

}