package com.dicoding.cocktailapps.data.api

import com.dicoding.cocktailapps.data.model.CocktailsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.*

interface ApiService {
    @GET("search.php")
    suspend fun getCocktails(
        @Query("s") search: String
    ): Flow<CocktailsResponse>

    @GET("lookup.php")
    suspend fun getCocktailDetail(
        @Query("i") id: String
    ): Flow<CocktailsResponse>
}