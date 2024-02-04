package com.markxh.pokedex.ui.feature.dashboard.data.repository

import com.markxh.pokedex.core.api.ApiService
import com.markxh.pokedex.ui.feature.dashboard.data.model.PokemonDetailsResponse
import com.markxh.pokedex.ui.feature.dashboard.data.model.PokemonListResponse
import com.markxh.pokedex.ui.feature.dashboard.data.model.PokemonResponse
import com.markxh.pokedex.ui.feature.dashboard.domain.repository.PokedexRepository
import retrofit2.Response

class PokedexRepositoryImpl(private val apiService: ApiService) : PokedexRepository {
    override suspend fun getPokemonList(): Result<List<PokemonResponse>?> {
        return try {
            val response: Response<PokemonListResponse> = apiService.getPokemonList(10)

            if (response.isSuccessful) {
                val pokemonList: List<PokemonResponse>? = response.body()?.results
                Result.success(pokemonList)
            } else {
                Result.failure(Exception("Failed to fetch Pokemon list. Response code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPokemonDetails(name: String): Result<PokemonDetailsResponse?> {
        return try {
            val response: Response<PokemonDetailsResponse> = apiService.getPokemonDetails(name)

            if (response.isSuccessful) {
                val pokemonDetails: PokemonDetailsResponse? = response.body()
                Result.success(pokemonDetails)
            } else {
                Result.failure(Exception("Failed to fetch Pokemon details. Response code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}