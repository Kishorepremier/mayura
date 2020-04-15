package com.primemover.mayura.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    //private const val  BASE_URL = "http://primeaccounts.in/mayura/index.php/api/"     //dev server
    private const val  BASE_URL = "http://mayurafinance.in/mayura/index.php/api/"      //prod server

    private val okHttpClient= OkHttpClient.Builder()
            .addInterceptor{chain ->
                val original = chain.request()

                val requestBuilder = original.newBuilder()
                        .method(original.method(), original.body())

                val request = requestBuilder.build()
                chain.proceed(request)
            }.build()

    val instance: APIInterface by lazy{
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        retrofit.create(APIInterface::class.java)
    }
}