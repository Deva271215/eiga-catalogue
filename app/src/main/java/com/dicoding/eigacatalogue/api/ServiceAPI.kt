package com.dicoding.eigacatalogue.api

import com.dicoding.eigacatalogue.source.remote.response.DetailMovieResponse
import com.dicoding.eigacatalogue.source.remote.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceAPI {
    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key") apiKey: String = "1aa4ffe3e3c4abc27682e226cbb6f180"
    ): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") id: Int?,
        @Query("api_key") apiKey: String = "1aa4ffe3e3c4abc27682e226cbb6f180"
    ): Call<DetailMovieResponse>

    @GET("tv/popular")
    fun getPopularTVShows(
        @Query("api_key") apiKey: String = "1aa4ffe3e3c4abc27682e226cbb6f180"
    ): Call<MovieResponse>

    @GET("tv/{tv_id}")
    fun getDetailTVShow(
        @Path("tv_id") id: Int?,
        @Query("api_key") apiKey: String = "1aa4ffe3e3c4abc27682e226cbb6f180"
    ): Call<DetailMovieResponse>
}