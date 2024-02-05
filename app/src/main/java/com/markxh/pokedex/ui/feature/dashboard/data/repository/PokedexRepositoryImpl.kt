package com.markxh.pokedex.ui.feature.dashboard.data.repository

import com.markxh.pokedex.core.api.ApiService
import com.markxh.pokedex.ui.feature.dashboard.data.model.PokemonListResponse
import com.markxh.pokedex.ui.feature.dashboard.data.model.PokemonResponse
import com.markxh.pokedex.ui.feature.dashboard.domain.model.Pokemon
import com.markxh.pokedex.ui.feature.dashboard.domain.repository.PokedexRepository
import com.markxh.pokedex.ui.feature.detail.data.model.PokemonDetailsResponse
import com.markxh.pokedex.ui.feature.detail.domain.model.PokemonDetails
import retrofit2.Response

class PokedexRepositoryImpl(private val apiService: ApiService) : PokedexRepository {
    override suspend fun getPokemonList(): Result<List<Pokemon>?> {
        return try {
            //todo add pagination
            val response: Response<PokemonListResponse> = apiService.getPokemonList(100)

            if (response.isSuccessful) {
                val pokemonList: List<PokemonResponse>? = response.body()?.results
                val pokemonListMapped: List<Pokemon> =
                    pokemonList?.map { Pokemon(it.name) } ?: emptyList()

                Result.success(pokemonListMapped)
            } else {
                Result.failure(Exception("Failed to fetch Pokemon list. Response code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPokemonDetails(name: String): Result<PokemonDetails?> {
        return try {
            val response: Response<PokemonDetailsResponse> = apiService.getPokemonDetails(name)

            if (response.isSuccessful) {
                val pokemonDetails: PokemonDetailsResponse? = response.body()
                val pokemonDetailsMapped = pokemonDetails?.let {
                    PokemonDetails(
                        it.name,
                        it.height,
                        it.weight,
                        it.sprites.other.officialArtwork.frontDefault
                    )
                }

                Result.success(pokemonDetailsMapped)
            } else {
                Result.failure(Exception("Failed to fetch Pokemon details. Response code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}