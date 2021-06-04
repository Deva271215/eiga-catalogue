package com.dicoding.eigacatalogue.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.eigacatalogue.MovieEntity
import com.dicoding.eigacatalogue.source.MovieRepository

class DetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private var id: Int? = 1

    fun getMovie(): LiveData<MovieEntity> = movieRepository.findMovie(id)
    fun getTVShow(): LiveData<MovieEntity> = movieRepository.findTVShow(id)

    fun insertFavorite(f: MovieEntity) = movieRepository.insertFavorite(f)

    fun setSelectedMovies(id: Int?) {
        this.id = id
    }
}