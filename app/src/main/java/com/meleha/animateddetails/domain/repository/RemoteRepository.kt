package com.meleha.animateddetails.domain.repository

import com.meleha.animateddetails.domain.models.FlickrItem
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {

    /**
     * Get the list of FlickrItem and listen for all further changes in the list
     */
    fun getFlickrItems(tag: String): Flow<List<FlickrItem>>
}