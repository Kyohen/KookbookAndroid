package kyo.hen.kookbook.features.Home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(padding: PaddingValues = PaddingValues(),
               navigateToDetailedView: (Int) -> Unit,
               navigateToAddRecipeView: () -> Unit
) {
    val recipes = listOf(
        RecipeSummary(
            name = "Blah blah",
            minutesToMake = 15,
            foodType = FoodType.CAKE,
            tags = listOf()
        ),
        RecipeSummary(
            name = "Blah blah",
            minutesToMake = 15,
            foodType = FoodType.CAKE,
            tags = listOf()
        ),
        RecipeSummary(
            name = "Blah blah",
            minutesToMake = 15,
            foodType = FoodType.CAKE,
            tags = listOf()
        ),
        RecipeSummary(
            name = "Blah blah",
            minutesToMake = 15,
            foodType = FoodType.CAKE,
            tags = listOf()
        ),
        RecipeSummary(
            name = "Blah blah",
            minutesToMake = 15,
            foodType = FoodType.CAKE,
            tags = listOf()
        ),
        RecipeSummary(
            name = "Blah blah",
            minutesToMake = 15,
            foodType = FoodType.CAKE,
            tags = listOf()
        )
    )

    val actionHandler: (HomeScreenAction) -> Unit = {
        when(it) {
            is HomeScreenAction.RecipeTapped -> navigateToDetailedView(it.id)
            HomeScreenAction.AddRecipeTapped -> navigateToAddRecipeView()
        }
    }
    Scaffold(
        modifier = Modifier.padding(padding),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { actionHandler(HomeScreenAction.AddRecipeTapped) },
            ) {
                Icon(Icons.Filled.Add, "Add Recipe")
            }
        },
    ) { innerPadding ->
        RecipeList(
            recipes = recipes,
            modifier = Modifier.padding(innerPadding),
            sendAction = actionHandler
        )
    }
}

sealed class HomeScreenAction {
    data class RecipeTapped(val id: Int): HomeScreenAction()
    object AddRecipeTapped: HomeScreenAction()
}