package com.meleha.animateddetails.data.repository

import com.meleha.animateddetails.data.remote.api.ApiService
import com.meleha.animateddetails.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val retrofitService: ApiService
) : RemoteRepository {

    override fun getFlickrItems(tag: String) = flow {
        emit(retrofitService.getFlickrItems(tag).items)
    }
}