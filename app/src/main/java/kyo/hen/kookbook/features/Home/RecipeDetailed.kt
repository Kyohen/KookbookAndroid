package kyo.hen.kookbook.features.Home

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class RecipeDetailed @OptIn(ExperimentalUuidApi::class) constructor(
    val name: String,
    val timeToMake: Int,
    val ingredients: List<Ingredient>,
    val instructions: List<Pair<Int, Instruction>>
)

data class Ingredient(
    val name: String,
    val quantity: Int,
    val unit: String
)

data class Instruction(
    val body: String
)