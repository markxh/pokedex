package com.markxh.pokedex.core.di

import com.markxh.pokedex.ui.feature.dashboard.domain.repository.PokedexRepository
import com.markxh.pokedex.ui.feature.dashboard.domain.usecase.GetPokemonUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    fun provideGetPokemonUseCase(pokedexRepository: PokedexRepository) =
        GetPokemonUseCase(pokedexRepository)
}