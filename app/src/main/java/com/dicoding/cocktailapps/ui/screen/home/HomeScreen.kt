package com.dicoding.cocktailapps.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.cocktailapps.data.di.Injection
import com.dicoding.cocktailapps.data.model.CocktailsResponse
import com.dicoding.cocktailapps.ui.ViewModelFactory
import com.dicoding.cocktailapps.ui.common.UiState
import com.dicoding.cocktailapps.ui.theme.CocktailAppsTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    onNavigateToDetailScreen: (String) -> Unit = {},
    onNavigateToFavoriteScreen: () -> Unit = {},
    onNavigateToAboutScreen: () -> Unit = {},
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getCocktails("")
            }
            is UiState.Success -> {
                HomeContent(
                    modifier = modifier,
                    data = uiState.data,
                    onNavigateToDetailScreen = onNavigateToDetailScreen,
                    onNavigateToFavoriteScreen = onNavigateToFavoriteScreen,
                    onNavigateToAboutScreen = onNavigateToAboutScreen,
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    data: CocktailsResponse,
    onNavigateToDetailScreen: (String) -> Unit = {},
    onNavigateToFavoriteScreen: () -> Unit = {},
    onNavigateToAboutScreen: () -> Unit = {},
) {

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CocktailAppsTheme {
        HomeScreen()
    }
}