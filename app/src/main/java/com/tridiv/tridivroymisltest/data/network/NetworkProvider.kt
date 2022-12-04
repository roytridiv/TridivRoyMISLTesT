package com.tridiv.tridivroymisltest.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.Executors


class NetworkProvider {

    private val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    private val baseUrl = "http://118.179.84.157:8014"

    val retofitApi2 = Retrofit.Builder()
        .client(OkHttpClient.Builder().apply {
            addInterceptor { chain: Interceptor.Chain ->
                val request =
                    chain.request().newBuilder()
                        .addHeader(
                            "Secret_Code",
                            "e92d1aa5f67ce24713cf638550f5daa84ef5ea3466ae29af8b1ad16fbe6c5fbb"
                        )
                        .build()
                chain.proceed(request)
            }

        }.build())
        .baseUrl(baseUrl)
        .addConverterFactory(
            MoshiConverterFactory.create(
                moshi
            )
        )
        .build()

    fun test() = retofitApi2.create(RestApiService::class.java)

}