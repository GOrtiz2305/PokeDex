package com.example.pokeapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String): Response<PokeApiDataResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") pokemonId: String): Response<PokeApiDetailResponse>
}