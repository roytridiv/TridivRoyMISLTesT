package com.tridiv.tridivroymisltest.data.network

import com.tridiv.tridivroymisltest.data.model.networkPojo.TvDetails.TvDetailsResponseBody
import com.tridiv.tridivroymisltest.data.model.networkPojo.TvListItems.TvListItemsResponseBody
import retrofit2.Response
import retrofit2.http.*

interface RestApiService {
    @GET("/products")
    suspend fun getTvListItems(): Response<TvListItemsResponseBody>

    @FormUrlEncoded
    @POST("/products/by_id")
    suspend fun getTvDetails(
        @Field("id") id: Int
    ): Response<TvDetailsResponseBody>
}