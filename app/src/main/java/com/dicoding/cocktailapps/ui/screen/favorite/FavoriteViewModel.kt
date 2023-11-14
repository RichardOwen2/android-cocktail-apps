package com.dicoding.cocktailapps.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.cocktailapps.data.model.CocktailEntity
import com.dicoding.cocktailapps.data.repository.CocktailRepository
import com.dicoding.cocktailapps.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: CocktailRepository
) : ViewModel() {
    private val _favoriteCocktails: MutableStateFlow<UiState<List<CocktailEntity>>> = MutableStateFlow(UiState.Loading)
    val favoriteCocktails: MutableStateFlow<UiState<List<CocktailEntity>>>
        get() = _favoriteCocktails

    fun getFavoriteCocktails() {
        viewModelScope.launch {
            repository.getFavoriteCocktails()
                .catch {
                    _favoriteCocktails.value = UiState.Error(it.message.toString())
                }
                .collect { cocktails ->
                    _favoriteCocktails.value = UiState.Success(cocktails)
                }
        }
    }
}