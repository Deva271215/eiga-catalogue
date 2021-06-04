package com.dicoding.eigacatalogue

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class MovieEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Int? = 0,

        @ColumnInfo(name = "image")
        val image: String? = "",

        @ColumnInfo(name = "title")
        val title: String? = "",

        @ColumnInfo(name = "air_year")
        val airYear: Int? = 0,

        @ColumnInfo(name = "genre")
        val genre: String? = "",

        @ColumnInfo(name = "duration")
        val dur: String? = "",

        @ColumnInfo(name = "score")
        val score: Double? = 0.0,

        @ColumnInfo(name = "tagline")
        val tagLine: String? = "",

        @ColumnInfo(name = "overview")
        val overview: String? = "",

        @ColumnInfo(name = "director")
        val director: String? = "",

        @ColumnInfo(name = "creator")
        val creator: String? = "",

        @ColumnInfo(name = "release")
        val release: String? = "",
 )