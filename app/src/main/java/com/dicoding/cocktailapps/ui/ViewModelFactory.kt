package com.dicoding.cocktailapps.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.cocktailapps.data.repository.CocktailRepository
import com.dicoding.cocktailapps.ui.screen.detail.DetailViewModel
import com.dicoding.cocktailapps.ui.screen.favorite.FavoriteViewModel
import com.dicoding.cocktailapps.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: CocktailRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}