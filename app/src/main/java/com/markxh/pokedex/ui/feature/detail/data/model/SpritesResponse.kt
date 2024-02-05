package com.markxh.pokedex.ui.feature.detail.data.model

import com.google.gson.annotations.SerializedName

data class SpritesResponse(
    @SerializedName("front_default")
    val frontDefault: String,
    val other: Other
)