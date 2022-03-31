package com.example.movies.data.db

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun integerListToString(genreIds: List<Int>): String = genreIds.joinToString()

    @TypeConverter
    fun stringToIntegerList(list: String): List<Int> = list.split(",").map { Integer.parseInt(it) }
}