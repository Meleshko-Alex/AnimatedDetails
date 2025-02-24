package com.meleha.animateddetails.di

import com.meleha.animateddetails.data.repository.RemoteRepositoryImpl
import com.meleha.animateddetails.domain.repository.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: RemoteRepositoryImpl
    ): RemoteRepository
}