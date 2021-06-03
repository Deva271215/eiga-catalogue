package com.dicoding.eigacatalogue.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse (
    @field:SerializedName("id")
    val id: Int? = 0,

    @field:SerializedName("title")
    val title: String? = "",

    @field:SerializedName("name")
    val name: String? = "",

    @field:SerializedName("poster_path")
    val image: String? = "",

    @field:SerializedName("overview")
    val overview: String? = "",

    @field:SerializedName("release_date")
    val release: String? = "",

    @field:SerializedName("tagline")
    val tagLine: String? = "",

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = "",

    @field:SerializedName("popularity")
    val popularity: Double? = 0.0,

    @field:SerializedName("created_by")
    val createdBy: List<CreatedBy>? = null,

    @field:SerializedName("genres")
    val genres: List<Genres>? = null,

    @field:SerializedName("score")
    val score: Double? = 0.0,
)

data class Genres(
        @field:SerializedName("name")
        val name: String? = "",
)

data class CreatedBy (
        @field:SerializedName("name")
        val name: String? = "",
)