package com.markxh.pokedex.ui.feature.detail.domain.usecase

import com.markxh.pokedex.ui.feature.dashboard.domain.repository.PokedexRepository

class GetPokemonDetailsUseCase(private val pokedexRepository: PokedexRepository) {
    suspend operator fun invoke(name: String) = pokedexRepository.getPokemonDetails(name)
}