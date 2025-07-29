package kyo.hen.kookbook.features.Detail

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import kotlinx.serialization.Serializable
import kyo.hen.kookbook.features.Home.Ingredient
import kyo.hen.kookbook.features.Home.Instruction
import kyo.hen.kookbook.features.Home.RecipeDetailed
import java.util.UUID
import kotlin.uuid.Uuid

@Serializable
data class DetailRoute(val id: Int)

@Composable
fun DetailedScreen(paddingValues: PaddingValues, navigateBack: () -> Unit) {
    val recipeDetailed = RecipeDetailed(
        name = "Blah blah",
        timeToMake = 15,
        ingredients = listOf(
            Ingredient("Onion", 5, ""),
            Ingredient("Butter", 250, "g")
        ),
        instructions = listOf(
            Pair(0, Instruction("Instruction 1")),
            Pair(1, Instruction("Instruction 2"))
        )
    )
    Scaffold(

        Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
        topBar = {
        TopBarWithAction {
            navigateBack()
        }
    }) { paddingValues ->
        DetailedView(recipeDetailed, modifier = Modifier.padding(paddingValues))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithAction(title: String = "Recipe", navigateBack: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                title,
            )
        },
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        },
    )
}