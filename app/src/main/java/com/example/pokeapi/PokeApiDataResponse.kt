package com.example.pokeapi

import com.google.gson.annotations.SerializedName

data class PokeApiDataResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("sprites") val sprites: PokemonSprites,
    @SerializedName("image") val image: String
)

data class PokemonResults (
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String
)

data class PokemonSprites(
    @SerializedName("front_default") val frontDefault: String
)

data class pokeImage (
    @SerializedName("url") val url: String

)