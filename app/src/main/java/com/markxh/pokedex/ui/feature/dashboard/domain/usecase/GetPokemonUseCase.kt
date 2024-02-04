package com.markxh.pokedex.ui.feature.dashboard.domain.usecase

import com.markxh.pokedex.ui.feature.dashboard.domain.repository.PokedexRepository

class GetPokemonUseCase(private val pokedexRepository: PokedexRepository) {
    suspend operator fun invoke() = pokedexRepository.getPokemonList()
}