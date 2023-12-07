package com.example.pokedex.data.remote.dto

import com.example.pokedex.data.remote.dto.pokemonList.PokemonData
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemonList")
    suspend fun getPokemonList(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
    ): PokemonData
}