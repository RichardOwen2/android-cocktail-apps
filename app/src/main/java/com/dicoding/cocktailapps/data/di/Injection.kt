package com.dicoding.cocktailapps.data.di

import android.content.Context
import com.dicoding.cocktailapps.data.api.ApiConfig
import com.dicoding.cocktailapps.data.local.FavoriteDatabase
import com.dicoding.cocktailapps.data.repository.CocktailRepository

object Injection {
    fun provideRepository(context: Context): CocktailRepository {
        val apiService = ApiConfig.getApiService()
        val favoriteDatabase = FavoriteDatabase.getInstance(context)
        val favoriteDao = favoriteDatabase.favoriteDao()
        return CocktailRepository.getInstance(apiService, favoriteDao)
    }
}