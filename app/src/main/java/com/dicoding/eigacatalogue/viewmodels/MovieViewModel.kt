package com.dicoding.eigacatalogue.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.eigacatalogue.DummyEntries
import com.dicoding.eigacatalogue.MovieEntity
import com.dicoding.eigacatalogue.source.MovieRepository

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getMovie(): LiveData<List<MovieEntity>> = movieRepository.fetchMovies()
}