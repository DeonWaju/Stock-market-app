package com.example.pokedex.data.remote.dto.pokemonDetails

data class PokemonDetails(
    val id: Int,
    val abilities: List<Ability>,
    val base_experience: Int,
    val forms: List<Form>,
    val height: Int,
    val is_default: Boolean,
    val weight: Int
)