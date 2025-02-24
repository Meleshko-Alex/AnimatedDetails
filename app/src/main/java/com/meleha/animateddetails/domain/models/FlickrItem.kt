package com.meleha.animateddetails.domain.models

    data class FlickrItem(
    val title: String,
    val link: String,
    val media: FlickrMedia,
    val published: String,
    val author: String,
    val tags: String
)
