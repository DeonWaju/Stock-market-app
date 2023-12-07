package com.example.pokedex.data.remote.dto.pokemonDetails

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)