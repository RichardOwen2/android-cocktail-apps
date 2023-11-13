package com.dicoding.cocktailapps.data.di

import com.dicoding.cocktailapps.data.api.ApiConfig
import com.dicoding.cocktailapps.data.repository.CocktailRepository

object Injection {
    fun provideRepository(): CocktailRepository {
        val apiService = ApiConfig.getApiService()
        return CocktailRepository.getInstance(apiService)
    }
}