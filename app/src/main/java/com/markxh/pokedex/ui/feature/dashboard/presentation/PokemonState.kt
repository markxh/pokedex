package com.markxh.pokedex.ui.feature.dashboard.presentation

import com.markxh.pokedex.ui.feature.dashboard.domain.model.Pokemon

sealed class PokemonState {
    data class Success(val pokemonList: List<Pokemon>) : PokemonState()
    data class Error(val errorMessage: String) : PokemonState()
    data object Loading : PokemonState()
}