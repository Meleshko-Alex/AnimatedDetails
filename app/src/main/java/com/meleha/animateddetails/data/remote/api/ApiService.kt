package com.meleha.animateddetails.data.remote.api

import com.meleha.animateddetails.domain.models.FlickrItem
import com.meleha.animateddetails.domain.models.FlickrResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("services/feeds/photos_public.gne?format=json&nojsoncallback=1")
    suspend fun getFlickrItems(@Query("tags") tags: String): FlickrResponse
}