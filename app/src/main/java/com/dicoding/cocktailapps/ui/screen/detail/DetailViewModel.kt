package com.dicoding.cocktailapps.ui.screen.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.cocktailapps.data.common.Result
import com.dicoding.cocktailapps.data.model.CocktailEntity
import com.dicoding.cocktailapps.data.model.DrinksItem
import com.dicoding.cocktailapps.data.repository.CocktailRepository
import com.dicoding.cocktailapps.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: CocktailRepository
) : ViewModel() {
    private val _drinkData = mutableStateOf<UiState<DrinksItem>>(UiState.Loading)
    val drinkData: State<UiState<DrinksItem>>
        get() = _drinkData

    private val _isFavorite: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isFavorite: MutableStateFlow<Boolean>
        get() = _isFavorite

    fun getCocktailDetail(id: String) {
        viewModelScope.launch {
            repository.getCocktailDetail(id).observeForever {
                when (it) {
                    is Result.Loading -> _drinkData.value = UiState.Loading
                    is Result.Success -> _drinkData.value = UiState.Success(it.data)
                    is Result.Error -> _drinkData.value = UiState.Error(it.error)
                }
            }
        }
    }

    fun isCocktailFavorite(id: String) {
        viewModelScope.launch {
            repository.isCocktailFavorite(id)
                .catch {
                    _isFavorite.value = false
                }
                .collect { isFavorite ->
                    _isFavorite.value = isFavorite
                }
        }
    }

    fun toggleFavoriteCocktail(cocktail: CocktailEntity) {
        viewModelScope.launch {
            if (_isFavorite.value) {
                repository.deleteFavoriteCocktail(cocktail)
            } else {
                repository.addFavoriteCocktail(cocktail)
            }
        }
    }
}