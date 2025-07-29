package kyo.hen.kookbook.Data

class RecipeRepository {

    private var recipes: MutableMap<Int, Recipe> = mutableMapOf()

    fun fetchRecipe(id: Int): Recipe {
        return recipes.getValue(id)
    }

    fun getAllRecipes(): List<Recipe> {
        return recipes.values.toList()
    }

    fun insertRecipe(recipe: Recipe) {
        recipes[recipe.id] = recipe
    }
}