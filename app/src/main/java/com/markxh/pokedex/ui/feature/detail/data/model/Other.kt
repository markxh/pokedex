package com.markxh.pokedex.ui.feature.detail.data.model

import com.google.gson.annotations.SerializedName

data class Other(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork
)