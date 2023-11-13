package com.dicoding.cocktailapps.ui.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.cocktailapps.data.di.Injection
import com.dicoding.cocktailapps.ui.ViewModelFactory
import com.dicoding.cocktailapps.ui.theme.CocktailAppsTheme

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
) {

}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel,
) {

}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    CocktailAppsTheme {
        DetailScreen()
    }
}