package com.dicoding.cocktailapps.ui.screen.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.cocktailapps.data.di.Injection
import com.dicoding.cocktailapps.data.model.CocktailEntity
import com.dicoding.cocktailapps.ui.ViewModelFactory
import com.dicoding.cocktailapps.ui.common.UiState
import com.dicoding.cocktailapps.ui.components.DrinkItem
import com.dicoding.cocktailapps.ui.components.Error
import com.dicoding.cocktailapps.ui.components.Loading
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
                Loading()
                viewModel.getFavoriteCocktails()
            }

            is UiState.Success -> {
                FavoriteContent(
                    modifier = modifier,
                    data = it.data,
                    onNavigateToDetailScreen = onNavigateToDetailScreen,
                )
            }

            is UiState.Error -> {
                Error(
                    message = it.errorMessage,
                    onRetry = { viewModel.getFavoriteCocktails() }
                )
            }
        }
    }
}

@Composable
fun FavoriteContent(
    modifier: Modifier = Modifier,
    data: List<CocktailEntity>,
    onNavigateToDetailScreen: (String) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (data.isNotEmpty()) {
            LazyColumn(
                state = rememberLazyListState(),
                contentPadding = PaddingValues(bottom = 10.dp)
            ) {
                items(data, key = { it.idDrink }) {
                    DrinkItem(
                        modifier = modifier,
                        data = it,
                        onNavigateToDetailScreen = onNavigateToDetailScreen
                    )
                }
            }
        } else {
            Text(
                text = "No Favorite Cocktails",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = modifier.align(alignment = Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    CocktailAppsTheme {
        FavoriteContent(
            data = listOf()
        )
    }
}