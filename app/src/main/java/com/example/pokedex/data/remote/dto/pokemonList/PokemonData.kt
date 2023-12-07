package com.example.pokedex.data.remote.dto.pokemonList

data class PokemonData(
    val count: Int,
    val next: Any,
    val previous: String,
    val results: List<Result>
)