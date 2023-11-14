package com.dicoding.cocktailapps.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.cocktailapps.data.di.Injection
import com.dicoding.cocktailapps.data.dummy.getDummyCocktailResponse
import com.dicoding.cocktailapps.data.model.CocktailsResponse
import com.dicoding.cocktailapps.ui.ViewModelFactory
import com.dicoding.cocktailapps.ui.common.UiState
import com.dicoding.cocktailapps.ui.theme.CocktailAppsTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context = LocalContext.current))
    ),
    onNavigateToDetailScreen: (String) -> Unit = {},
    onNavigateToFavoriteScreen: () -> Unit = {},
    onNavigateToAboutScreen: () -> Unit = {},
) {
    val cocktailsData by viewModel.cocktailsData

    val performSearch = { search: String ->
        viewModel.getCocktails(search)
    }

    LaunchedEffect(key1 = viewModel) {
        viewModel.getCocktails("")
    }

    when (cocktailsData) {
        is UiState.Loading -> {

        }

        is UiState.Success -> {
            HomeContent(
                modifier = modifier,
                data = (cocktailsData as UiState.Success<CocktailsResponse>).data,
                performSearch = performSearch,
                onNavigateToDetailScreen = onNavigateToDetailScreen,
                onNavigateToFavoriteScreen = onNavigateToFavoriteScreen,
                onNavigateToAboutScreen = onNavigateToAboutScreen,
            )
        }

        is UiState.Error -> {}
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    data: CocktailsResponse,
    performSearch: (String) -> Unit = {},
    onNavigateToDetailScreen: (String) -> Unit = {},
    onNavigateToFavoriteScreen: () -> Unit = {},
    onNavigateToAboutScreen: () -> Unit = {},
) {

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CocktailAppsTheme {
        HomeContent(
            data = getDummyCocktailResponse(),
        )
    }
}