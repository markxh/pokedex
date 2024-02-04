package com.markxh.pokedex.core.di

import com.markxh.pokedex.core.api.ApiService
import com.markxh.pokedex.ui.feature.dashboard.data.repository.PokedexRepositoryImpl
import com.markxh.pokedex.ui.feature.dashboard.domain.repository.PokedexRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePokedexRepository(apiService: ApiService) : PokedexRepository {
        return PokedexRepositoryImpl(apiService)
    }
}