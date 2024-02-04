package com.markxh.pokedex.ui.feature.dashboard.domain.repository

import com.markxh.pokedex.ui.feature.dashboard.data.model.Pokemon
import retrofit2.http.GET

interface PokedexRepository {
    @GET("pokemon")
    suspend fun getPokemon() : Result<List<Pokemon>?>
}