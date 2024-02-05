package com.markxh.pokedex.core.api

import com.markxh.pokedex.ui.feature.dashboard.data.model.PokemonListResponse
import com.markxh.pokedex.ui.feature.detail.data.model.PokemonDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int): Response<PokemonListResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(@Path("name") name: String): Response<PokemonDetailsResponse>
}