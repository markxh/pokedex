package com.markxh.pokedex.ui.feature.dashboard.data.repository

import com.markxh.pokedex.core.api.ApiService
import com.markxh.pokedex.ui.feature.dashboard.data.model.Pokemon
import com.markxh.pokedex.ui.feature.dashboard.data.model.PokemonListResponse
import com.markxh.pokedex.ui.feature.dashboard.domain.repository.PokedexRepository
import retrofit2.Response

class PokedexRepositoryImpl(private val apiService: ApiService) : PokedexRepository {
    override suspend fun getPokemon(): Result<List<Pokemon>?> {
        return try {
            val response: Response<PokemonListResponse> = apiService.getPokemonList(10)

            if (response.isSuccessful) {
                val pokemonList: List<Pokemon>? = response.body()?.results
                Result.success(pokemonList)
            } else {
                Result.failure(Exception("Failed to fetch Pokémon. Response code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}