package com.markxh.pokedex.ui.feature.detail.presentation

import com.markxh.pokedex.ui.feature.detail.domain.model.PokemonDetails

sealed class PokemonDetailsState {
    data class Success(val pokemonDetails: PokemonDetails) : PokemonDetailsState()
    data class Error(val errorMessage: String) : PokemonDetailsState()
    data object Loading : PokemonDetailsState()
}