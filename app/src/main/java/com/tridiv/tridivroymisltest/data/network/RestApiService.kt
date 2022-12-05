package com.tridiv.tridivroymisltest.data.network


import com.tridiv.tridivroymisltest.data.model.networkPojo.TvDetails.TvDetailsResponseBody
import com.tridiv.tridivroymisltest.data.model.networkPojo.TvDetailsReqBody
import com.tridiv.tridivroymisltest.data.model.networkPojo.TvListItems.TvListItemsResponseBodyItem
import retrofit2.Response
import retrofit2.http.*

interface RestApiService {

    @GET("/products")
    suspend fun getTvListItems(): Response<List<TvListItemsResponseBodyItem>>


    @POST("/products/by_id")
    suspend fun getTvDetails(
        @Body body: TvDetailsReqBody
    ): Response<TvDetailsResponseBody>
}