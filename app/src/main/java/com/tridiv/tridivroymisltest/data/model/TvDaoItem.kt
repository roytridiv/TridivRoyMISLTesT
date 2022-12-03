package com.tridiv.tridivroymisltest.data.model

import androidx.room.*
import com.tridiv.tridivroymisltest.presenter.model.TvData


@Entity
data class TvDaoItem(
    @ColumnInfo(name = "page") val pageNo: String,
    @ColumnInfo(name = "tv_id") val tvId: Int,
    @ColumnInfo(name = "tv_image") val image: String,
    @ColumnInfo(name = "tv_name") val name: String,
    @ColumnInfo(name = "tv_model") val model: String,
    @ColumnInfo(name = "tv_size") val tvSize: String,
    @ColumnInfo(name = "tv_price") val price: String,
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
)