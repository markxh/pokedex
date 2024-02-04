package com.markxh.pokedex.core.api

import com.markxh.pokedex.ui.feature.dashboard.data.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int): Response<PokemonListResponse>
}