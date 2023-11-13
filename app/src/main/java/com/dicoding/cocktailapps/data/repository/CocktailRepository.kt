package com.dicoding.cocktailapps.data.repository

import com.dicoding.cocktailapps.data.api.ApiService

class CocktailRepository private constructor(
    private val apiService: ApiService,
) {

    suspend fun getCocktails(search: String) = apiService.getCocktails(search)

    suspend fun getCocktailDetail(id: String) = apiService.getCocktailDetail(id)

    companion object {
        @Volatile
        private var instance: CocktailRepository? = null
        fun getInstance(
            apiService: ApiService
        ): CocktailRepository =
            instance ?: synchronized(this) {
                instance ?: CocktailRepository(apiService)
            }.also { instance = it }
    }
}