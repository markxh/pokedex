package com.markxh.pokedex.ui.feature.dashboard.presentation

import com.markxh.pokedex.ui.feature.dashboard.data.model.PokemonResponse

sealed class PokemonState {
    data class Success(val pokemonList: List<PokemonResponse>) : PokemonState()
    data class Error(val errorMessage: String) : PokemonState()
    data object Loading : PokemonState()
}