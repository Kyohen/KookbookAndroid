package kyo.hen.kookbook.Data

import kyo.hen.kookbook.features.Home.Ingredient
import kyo.hen.kookbook.features.Home.Instruction

data class Recipe(
    val id: Int,
    val name: String,
    val instructions: List<Instruction>,
    val timeToMake: Int,
    val ingredients: Set<Ingredient>
)