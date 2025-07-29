package kyo.hen.kookbook.features.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme

// TODO: FoodType Enum to become a preset image
@Composable
fun RecipeCell(recipe: RecipeSummary, onClick: (Int) -> Unit) {
    Card(
        Modifier.padding(16.dp).clickable { onClick(-1) },
        colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
    )) {
        Row(
            Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RecipeIcon(type = recipe.foodType)

            Column {
                Text(recipe.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
                Card {
                    Text(
                        text = "Minutes To Cook: ${recipe.minutesToMake}m",
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.padding(6.dp),
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.White
                        )
                }
            }

            Spacer(Modifier.weight(1f))
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null)
        }
    }
}

@Composable
fun RecipeIcon(type: FoodType) {
    Image(
        imageVector = ImageVector.vectorResource(type.imageVector),
        contentDescription = type.contentDesc,
        contentScale = ContentScale.Fit
    )
}
@Preview(showBackground = true)
@Composable
fun RecipeList_Preview() {

    val recipe = RecipeSummary(
        name = "Recipe 1",
        minutesToMake = 15,
        foodType = FoodType.CAKE,
        tags = listOf("Food", "Main"))
    AppTheme {
//        RecipeCell(
//            recipe,
//            onClick = { }
//        )
    }
}