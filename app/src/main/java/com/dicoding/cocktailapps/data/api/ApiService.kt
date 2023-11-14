package com.dicoding.cocktailapps.data.api

import com.dicoding.cocktailapps.data.model.CocktailsResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("search.php")
    suspend fun getCocktails(
        @Query("s") search: String
    ): Response<CocktailsResponse>

    @GET("lookup.php")
    suspend fun getCocktailDetail(
        @Query("i") id: String
    ): Response<CocktailsResponse>
}