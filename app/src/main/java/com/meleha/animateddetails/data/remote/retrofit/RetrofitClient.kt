package com.meleha.animateddetails.data.remote.retrofit

import com.meleha.animateddetails.data.remote.api.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitClient {

    companion object {
        private val instance: Retrofit by lazy {
            val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
            val okHttp = OkHttpClient.Builder()
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttp)
                .build()
        }


        val service: ApiService = instance.create(ApiService::class.java)

        private const val BASE_URL = "https://api.flickr.com/"
    }
}