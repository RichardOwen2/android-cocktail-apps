package com.dicoding.cocktailapps.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.cocktailapps.data.repository.CocktailRepository
import com.dicoding.cocktailapps.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: CocktailRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
//        } else if (modelClass.isAssignableFrom(DetailRewardViewModel::class.java)) {
//            return DetailRewardViewModel(repository) as T
//        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
//            return CartViewModel(repository) as T
//        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}