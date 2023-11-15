package com.dicoding.cocktailapps.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.cocktailapps.data.di.Injection
import com.dicoding.cocktailapps.data.model.CocktailsResponse
import com.dicoding.cocktailapps.ui.ViewModelFactory
import com.dicoding.cocktailapps.ui.common.UiState
import com.dicoding.cocktailapps.ui.components.DrinkItem
import com.dicoding.cocktailapps.ui.components.Error
import com.dicoding.cocktailapps.ui.components.Loading
import com.dicoding.cocktailapps.ui.components.SearchBarComponent

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context = LocalContext.current))
    ),
    onNavigateToDetailScreen: (String) -> Unit,
) {
    val cocktailsData by viewModel.cocktailsData

    val performSearch = { search: String ->
        viewModel.getCocktails(search)
    }

    val listState = rememberLazyListState()
    val query = remember { mutableStateOf("") }

    val onRetry = { viewModel.getCocktails(query.value) }


    LaunchedEffect(key1 = viewModel) {
        if (cocktailsData is UiState.Loading) {
            viewModel.getCocktails("")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column {
            SearchBarComponent(
                modifier = modifier,
                query = query.value,
                onQueryChange = { text: String -> query.value = text },
                onSearch = performSearch,
            )

            Spacer(modifier = Modifier.height(6.dp))

            when (cocktailsData) {
                is UiState.Loading -> {
                    Loading()
                }

                is UiState.Success -> {
                    val drinks = (cocktailsData as UiState.Success<CocktailsResponse>).data.drinks!!

                    LazyColumn(
                        state = listState,
                        contentPadding = PaddingValues(bottom = 10.dp)
                    ) {
                        items(drinks, key = { it.idDrink }) {
                            DrinkItem(
                                modifier = modifier,
                                data = it,
                                onNavigateToDetailScreen = onNavigateToDetailScreen
                            )
                        }
                    }
                }

                is UiState.Error -> {
                    Error(
                        message = (cocktailsData as UiState.Error).errorMessage,
                        onRetry = onRetry,
                    )
                }
            }
        }
    }


}

//@Composable
//fun HomeContent(
//    modifier: Modifier = Modifier,
//    data: List<DrinksItem>,
//    onNavigateToDetailScreen: (String) -> Unit = {},
//) {
//
//}
//
//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    CocktailAppsTheme {
//        HomeContent(
//            data = getDummyCocktailResponse(),
//        )
//    }
//}