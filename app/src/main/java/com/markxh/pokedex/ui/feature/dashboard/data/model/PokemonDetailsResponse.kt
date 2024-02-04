package com.markxh.pokedex.ui.feature.dashboard.data.model

data class PokemonDetailsResponse(
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: SpritesResponse
)