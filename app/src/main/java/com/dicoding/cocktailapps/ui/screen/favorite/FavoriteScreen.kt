package com.dicoding.cocktailapps.ui.screen.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.cocktailapps.data.di.Injection
import com.dicoding.cocktailapps.data.dummy.getDummyCocktailEntity
import com.dicoding.cocktailapps.data.model.CocktailEntity
import com.dicoding.cocktailapps.ui.ViewModelFactory
import com.dicoding.cocktailapps.ui.common.UiState
import com.dicoding.cocktailapps.ui.theme.CocktailAppsTheme

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context = LocalContext.current))
    ),
    onNavigateToDetailScreen: (String) -> Unit,
) {
    viewModel.favoriteCocktails.collectAsState(initial = UiState.Loading).value.let {
        when (it) {
            is UiState.Loading -> {
                viewModel.getFavoriteCocktails()
            }

            is UiState.Success -> {
                FavoriteContent(
                    modifier = modifier,
                    data = it.data,
                    onNavigateToDetailScreen = onNavigateToDetailScreen,
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun FavoriteContent(
    modifier: Modifier = Modifier,
    data: List<CocktailEntity>,
    onNavigateToDetailScreen: (String) -> Unit = {},
) {

}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    CocktailAppsTheme {
        FavoriteContent(
            data = getDummyCocktailEntity()
        )
    }
}