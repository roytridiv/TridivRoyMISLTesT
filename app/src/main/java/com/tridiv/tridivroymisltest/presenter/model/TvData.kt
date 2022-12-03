package com.tridiv.tridivroymisltest.presenter.model

import java.io.Serializable

data class TvData(
    val tvId: Int,
    val image: String,
    val name: String,
    val model: String,
    val tvSize: String,
    val prize: String
) : Serializable