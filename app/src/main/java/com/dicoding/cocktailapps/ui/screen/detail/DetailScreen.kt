package com.dicoding.cocktailapps.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.dicoding.cocktailapps.data.di.Injection
import com.dicoding.cocktailapps.data.dummy.getDummyCocktailResponse
import com.dicoding.cocktailapps.data.model.DrinksItem
import com.dicoding.cocktailapps.ui.ViewModelFactory
import com.dicoding.cocktailapps.ui.common.UiState
import com.dicoding.cocktailapps.ui.components.Error
import com.dicoding.cocktailapps.ui.components.Loading
import com.dicoding.cocktailapps.ui.theme.CocktailAppsTheme
import com.dicoding.cocktailapps.ui.utils.extractIngredients

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

    val onRetry = {
        viewModel.getCocktailDetail(id)
        viewModel.isCocktailFavorite(id)
    }

    DisposableEffect(id) {
        viewModel.isCocktailFavorite(id)
        viewModel.getCocktailDetail(id)
        onDispose {

        }
    }

    when (drinkData) {
        is UiState.Loading -> {
            Loading()
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
            )
        }

        is UiState.Error -> {
            Error(
                message = (drinkData as UiState.Error).errorMessage,
                onRetry = onRetry,
            )
        }
    }
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    data: DrinksItem,
    isFavorite: Boolean,
    toggleFavorite: () -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = data.strDrinkThumb,
                contentDescription = data.strDrink + " Image",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
            Spacer(modifier = modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(
                        start = 8.dp,
                        end = 8.dp,
                        bottom = 45.dp
                    )
            ) {
                Text(
                    text = data.strDrink,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = "${data.strCategory} (${data.strAlcoholic})",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = "Ingredients",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = modifier.height(12.dp))
                data.extractIngredients().forEach {
                    Text(
                        text = it,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = modifier.height(12.dp))
                Text(
                    text = "Instructions",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = modifier.height(12.dp))
                Text(
                    text = data.strInstructions,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        FloatingActionButton(
            onClick = toggleFavorite,
            modifier = Modifier
                .padding(16.dp),
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = if (isFavorite) "Remove from Favorites" else "Add to Favorites"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    CocktailAppsTheme {
        DetailContent(
            data = getDummyCocktailResponse().drinks!![0],
            isFavorite = false,
            toggleFavorite = {},
        )
    }
}