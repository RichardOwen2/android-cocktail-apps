package com.dicoding.cocktailapps.ui.screen.home

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.cocktailapps.data.common.Result
import com.dicoding.cocktailapps.data.model.CocktailsResponse
import com.dicoding.cocktailapps.data.repository.CocktailRepository
import com.dicoding.cocktailapps.ui.common.UiState
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: CocktailRepository
) : ViewModel() {
    private val _cocktailsData = mutableStateOf<UiState<CocktailsResponse>>(UiState.Loading)
    val cocktailsData: State<UiState<CocktailsResponse>>
        get() = _cocktailsData

    fun getCocktails(search: String) {
        viewModelScope.launch {
            repository.getCocktails(search).observeForever {
                when (it) {
                    is Result.Loading -> _cocktailsData.value = UiState.Loading
                    is Result.Success -> _cocktailsData.value = UiState.Success(it.data)
                    is Result.Error -> _cocktailsData.value = UiState.Error(it.error)
                }
            }
        }
    }
}