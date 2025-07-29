package kyo.hen.kookbook.features.Detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kyo.hen.kookbook.features.Home.Ingredient
import kyo.hen.kookbook.features.Home.Instruction
import kyo.hen.kookbook.features.Home.RecipeDetailed

@Composable
fun DetailedView(recipe: RecipeDetailed, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        item {
            Title(recipe.name, recipe.timeToMake)
        }

        item {
            IngredientsCard(ingredients = recipe.ingredients)
        }

        item {
            InstructionsCard(instruction = recipe.instructions)
        }

    }
}

@Composable
fun CardView(content: @Composable ColumnScope.() -> Unit) {
    Card(
        Modifier.fillMaxSize().padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        content()
    }
}
@Composable
fun Title(text: String, minutesToCook: Int) {
    Card(
        Modifier.padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)) {
        Column(Modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Minutes To Make: $minutesToCook",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable fun IngredientsCard(ingredients: List<Ingredient>) {
    CardView {
        Column(Modifier.padding(16.dp)) {
            ingredients.forEach {
                Text("${it.name}, ${it.quantity}${it.unit}")
            }
        }
    }
}

@Composable fun InstructionsCard(instruction: List<Pair<Int, Instruction>>) {
    CardView {
        Column(Modifier.padding(16.dp)) {
            instruction.forEach {
                Card(Modifier.padding(16.dp)) {
                    Column(Modifier.fillMaxWidth().padding(16.dp)) {
                        Text(it.first.toString(), modifier = Modifier.align(Alignment.CenterHorizontally))
                        Text(it.second.body)
                    }
                }
            }
        }
    }
}