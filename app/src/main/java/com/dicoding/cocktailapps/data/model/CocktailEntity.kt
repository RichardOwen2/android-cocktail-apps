package com.dicoding.cocktailapps.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktail")
class CocktailEntity (
    @field:ColumnInfo(name = "idDrink")
    @field:PrimaryKey
    val idDrink: String,

    @field:ColumnInfo(name = "strDrink")
    val strDrink: String? = null,

    @field:ColumnInfo(name = "strDrinkThumb")
    val strDrinkThumb: String? = null,
)