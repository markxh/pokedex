package com.markxh.pokedex.ui.feature.dashboard.domain.repository

import com.markxh.pokedex.ui.feature.dashboard.data.model.PokemonDetailsResponse
import com.markxh.pokedex.ui.feature.dashboard.data.model.PokemonResponse

interface PokedexRepository {
    suspend fun getPokemonList(): Result<List<PokemonResponse>?>

    suspend fun getPokemonDetails(name: String): Result<PokemonDetailsResponse?>
}