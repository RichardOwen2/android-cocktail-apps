package com.dicoding.cocktailapps.ui.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.cocktailapps.data.di.Injection
import com.dicoding.cocktailapps.data.model.DrinksItem
import com.dicoding.cocktailapps.ui.ViewModelFactory
import com.dicoding.cocktailapps.ui.common.UiState
import com.dicoding.cocktailapps.ui.theme.CocktailAppsTheme

@Composable
fun DetailScreen(
    id: String,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context = LocalContext.current))
    ),
) {
    val drinkData by viewModel.drinkData
    val isFavorite by viewModel.isFavorite.collectAsState()

    DisposableEffect(id) {
        viewModel.isCocktailFavorite(id)
        viewModel.getCocktailDetail(id)
        onDispose {

        }
    }

    when (drinkData) {
        is UiState.Loading -> {

        }

        is UiState.Success -> {
            val toggleFavorite = {
                viewModel.toggleFavoriteCocktail((drinkData as UiState.Success<DrinksItem>).data.toCocktailEntity())
            }

            DetailContent(
                modifier = modifier,
                data = (drinkData as UiState.Success<DrinksItem>).data,
                isFavorite = isFavorite,
                toggleFavorite = toggleFavorite,
                viewModel = viewModel,
            )
        }

        is UiState.Error -> {}
    }
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    data: DrinksItem,
    isFavorite: Boolean,
    toggleFavorite: () -> Unit = {},
    viewModel: DetailViewModel,
) {

}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    CocktailAppsTheme {
        DetailScreen("1231")
    }
}