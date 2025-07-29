package kyo.hen.kookbook.features.Home

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import kyo.hen.kookbook.R
import java.util.UUID
import kotlin.uuid.ExperimentalUuidApi

data class RecipeSummary(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val minutesToMake: Int,
    val foodType: FoodType,
    val tags: List<String>
)

//enum class FoodType {
//    DINNER, DESSERT, SOUP, CAKE
//}

sealed class FoodType(@DrawableRes val imageVector: Int, val contentDesc: String) {
    data object DINNER: FoodType(R.drawable.baseline_dinner_dining_24, contentDesc = "dinner")
    data object BAKED: FoodType(R.drawable.round_bakery_dining_24, contentDesc = "baking")
    data object SOUP: FoodType(R.drawable.outline_cooking_24, contentDesc = "soup")
    data object CAKE: FoodType(R.drawable.baseline_cake_24, contentDesc = "cake")
}