package com.dicoding.eigacatalogue.source

import androidx.lifecycle.LiveData
import com.dicoding.eigacatalogue.MovieEntity

interface MovieDataSource {
    fun fetchMovies(): LiveData<List<MovieEntity>>
    fun findMovie(id: Int?): LiveData<MovieEntity>
    fun fetchTVShows(): LiveData<List<MovieEntity>>
    fun findTVShow(id: Int?): LiveData<MovieEntity>

    fun insertFavorite(f: MovieEntity)
}