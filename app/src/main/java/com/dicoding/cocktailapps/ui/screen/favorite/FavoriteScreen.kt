package com.dicoding.cocktailapps.ui.screen.favorite

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.cocktailapps.data.di.Injection
import com.dicoding.cocktailapps.ui.ViewModelFactory
import com.dicoding.cocktailapps.ui.theme.CocktailAppsTheme

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
) {

}

@Composable
fun FavoriteContent(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel,
) {

}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    CocktailAppsTheme {
        FavoriteScreen()
    }
}