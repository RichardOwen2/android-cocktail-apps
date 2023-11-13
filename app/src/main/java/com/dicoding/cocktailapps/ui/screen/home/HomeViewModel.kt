package com.dicoding.cocktailapps.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.cocktailapps.data.model.CocktailsResponse
import com.dicoding.cocktailapps.data.repository.CocktailRepository
import com.dicoding.cocktailapps.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: CocktailRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CocktailsResponse>> = MutableStateFlow(UiState.Loading)
    val uiState: MutableStateFlow<UiState<CocktailsResponse>>
        get() = _uiState

    fun getCocktails(search: String) {
        viewModelScope.launch {
            repository.getCocktails(search)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { cocktails ->
                    _uiState.value = UiState.Success(cocktails)
                }
        }
    }
}