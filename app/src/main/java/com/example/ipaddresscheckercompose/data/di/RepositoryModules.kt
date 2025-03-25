package com.example.ipaddresscheckercompose.data.di

import com.example.ipaddresscheckercompose.domain.repository.IpRepository
import com.example.ipaddresscheckercompose.domain.repository.IpRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModules {

    @Singleton
    @Binds
    abstract fun provideIpRepository(ipRepositoryImpl: IpRepositoryImpl): IpRepository
}