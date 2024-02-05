package com.markxh.pokedex.ui.feature.dashboard.domain.repository

import com.markxh.pokedex.ui.feature.dashboard.domain.model.Pokemon
import com.markxh.pokedex.ui.feature.detail.domain.model.PokemonDetails

interface PokedexRepository {
    suspend fun getPokemonList(): Result<List<Pokemon>?>

    suspend fun getPokemonDetails(name: String): Result<PokemonDetails?>
}