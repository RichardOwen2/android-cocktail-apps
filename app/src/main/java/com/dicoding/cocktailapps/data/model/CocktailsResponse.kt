package com.dicoding.cocktailapps.data.model

data class CocktailsResponse(
	val drinks: List<DrinksItem>? = null
)

data class DrinksItem(
	val idDrink: String,
	val strDrink: String,
	val strCategory: String,
	val strAlcoholic: String,
	val strInstructions: String? = null,
	val strDrinkThumb: String,
	val strIngredient1: String? = null,
	val strIngredient2: String? = null,
	val strIngredient3: String? = null,
	val strIngredient4: String? = null,
	val strIngredient5: String? = null,
	val strIngredient6: String? = null,
	val strIngredient7: String? = null,
	val strIngredient8: String? = null,
	val strIngredient9: String? = null,
	val strIngredient10: String? = null,
	val strIngredient11: String? = null,
	val strIngredient12: String? = null,
	val strIngredient13: String? = null,
	val strIngredient14: String? = null,
	val strIngredient15: String? = null,
	val strMeasure1: String? = null,
	val strMeasure2: String? = null,
	val strMeasure3: String? = null,
	val strMeasure4: String? = null,
	val strMeasure5: String? = null,
	val strMeasure6: String? = null,
	val strMeasure7: String? = null,
	val strMeasure8: String? = null,
	val strMeasure9: String? = null,
	val strMeasure10: String? = null,
	val strMeasure11: String? = null,
	val strMeasure12: String? = null,
	val strMeasure13: String? = null,
	val strMeasure14: String? = null,
	val strMeasure15: String? = null,
) {
	fun toCocktailEntity(): CocktailEntity {
		return CocktailEntity(
			idDrink = this.idDrink,
			strDrink = this.strDrink,
			strDrinkThumb = this.strDrinkThumb,
			strCategory = this.strCategory,
			strAlcoholic = this.strAlcoholic,
		)
	}
}

