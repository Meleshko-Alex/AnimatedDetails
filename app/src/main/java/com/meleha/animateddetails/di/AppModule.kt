package com.meleha.animateddetails.di

import com.meleha.animateddetails.data.remote.api.ApiService
import com.meleha.animateddetails.data.remote.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService = RetrofitClient.service
}