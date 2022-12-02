package com.tridiv.tridivroymisltest.data.network

import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.Executors


class NetworkProvider {

    private val baseUrl = "http://118.179.84.157:8014/"



    val interceptor = HttpLoggingInterceptor()
    var client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor { chain: Interceptor.Chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader(
                    "Authorization",
                    "e92d1aa5f67ce24713cf638550f5daa84ef5ea3466ae29af8b1ad16fbe6c5fbb"
                )
                .build()
            chain.proceed(request)
        }
        .build()

    var retrofitApi: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().build()
            )
        )
        .build()



//    val retofitApi = Retrofit.Builder()
//        .client(OkHttpClient.Builder().apply {
//            if (BuildConfig.DEBUG) {
//                val interceptor = HttpLoggingInterceptor()
//                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//                addInterceptor(BaseUrlChangerInterceptor(baseUrl) {
//                    mutableBaseUrl
//                })
//                addInterceptor(
//                    LoggingInterceptor.Builder()
//                        .loggable(BuildConfig.DEBUG)
//                        .setLevel(Level.BASIC)
//                        .log(Platform.INFO)
//                        .request("API_REQ")
//                        .response("API_RES")
//                        .executor(Executors.newSingleThreadExecutor())
//                        .enableAndroidStudio_v3_LogsHack(true)
//                        .build()
//                )
//                addInterceptor(ChuckInterceptor(context))
//                addInterceptor(interceptor)
//            }
//
//        }.build())
//        .baseUrl(baseUrl)
//        .addConverterFactory(
//            MoshiConverterFactory.create(
//                Moshi.Builder()
//                    .build()
//            )
//        )
//        .build()

}