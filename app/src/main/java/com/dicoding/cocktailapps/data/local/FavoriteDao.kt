package com.dicoding.cocktailapps.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.cocktailapps.data.model.CocktailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM cocktail")
    fun getFavoriteCocktails(): Flow<List<CocktailEntity>>

    @Query("SELECT EXISTS(SELECT * FROM cocktail WHERE idDrink = :idDrink)")
    fun isFavorite(idDrink: String): Flow<Boolean>

    @Query("DELETE FROM cocktail WHERE idDrink = :idDrink")
    suspend fun delete(idDrink: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cocktail: CocktailEntity)
}