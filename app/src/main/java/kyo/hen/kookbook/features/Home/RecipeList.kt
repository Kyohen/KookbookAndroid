package kyo.hen.kookbook.features.Home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.AppTheme

@Composable
fun RecipeList(recipes: List<RecipeSummary>,
               modifier: Modifier = Modifier,
               sendAction: (HomeScreenAction) -> Unit
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(recipes) {
            RecipeCell(
                recipe = it,
                onClick = { sendAction(HomeScreenAction.RecipeTapped(it))}

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeCell_Preview() {
    AppTheme {
//        RecipeList(listOf())
    }
}