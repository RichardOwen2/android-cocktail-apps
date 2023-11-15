package com.dicoding.cocktailapps.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.cocktailapps.data.api.ApiService
import com.dicoding.cocktailapps.data.common.Result
import com.dicoding.cocktailapps.data.local.FavoriteDao
import com.dicoding.cocktailapps.data.model.CocktailEntity
import com.dicoding.cocktailapps.data.model.CocktailsResponse
import com.dicoding.cocktailapps.data.model.DrinksItem
import kotlinx.coroutines.Dispatchers

class CocktailRepository private constructor(
    private val apiService: ApiService,
    private val favoriteDao: FavoriteDao
) {

//    suspend fun getCocktails(search: String) = apiService.getCocktails(search)

    fun getCocktails(search: String): LiveData<Result<CocktailsResponse>> = liveData(Dispatchers.IO) {
        emit(Result.Loading)

        try {
            val response = apiService.getCocktails(search)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    if (!body.drinks.isNullOrEmpty()) {
                        emit(Result.Success(body))
                    } else {
                        emit(Result.Error("Not Found"))
                    }
                } else {
                    emit(Result.Error("Something went wrong"))
                }
            } else {
                emit(Result.Error("Something went wrong"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getCocktailDetail(id: String): LiveData<Result<DrinksItem>> = liveData(Dispatchers.IO) {
        emit(Result.Loading)

        try {
            val response = apiService.getCocktailDetail(id)
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.drinks != null && body.drinks.isNotEmpty()) {
                    emit(Result.Success(body.drinks[0]))
                } else {
                    emit(Result.Error("Not Found"))
                }
            } else {
                emit(Result.Error("Something went wrong"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun isCocktailFavorite(id: String) = favoriteDao.isFavorite(id)

    fun getFavoriteCocktails() = favoriteDao.getFavoriteCocktails()

    suspend fun deleteFavoriteCocktail(cocktail: CocktailEntity) =
        favoriteDao.delete(cocktail.idDrink)

    suspend fun addFavoriteCocktail(cocktail: CocktailEntity) = favoriteDao.insert(cocktail)

    companion object {
        @Volatile
        private var instance: CocktailRepository? = null
        fun getInstance(
            apiService: ApiService,
            favoriteDao: FavoriteDao
        ): CocktailRepository =
            instance ?: synchronized(this) {
                instance ?: CocktailRepository(apiService, favoriteDao)
            }.also { instance = it }
    }
}