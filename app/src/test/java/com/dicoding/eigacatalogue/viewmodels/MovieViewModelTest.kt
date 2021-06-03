package com.dicoding.eigacatalogue.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.eigacatalogue.DummyEntries
import com.dicoding.eigacatalogue.MovieEntity
import com.dicoding.eigacatalogue.source.MovieRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExtRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var obs: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovie() {
        val moviesDummy = DummyEntries.getMovieDummy()
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = moviesDummy

        Mockito.`when`(movieRepository.fetchMovies()).thenReturn(movies)
        val movieEntities = viewModel.getMovie().value
        Mockito.verify(movieRepository).fetchMovies()
        Assert.assertNotNull(movieEntities)
        Assert.assertEquals(1, movieEntities?.size)

        viewModel.getMovie().observeForever(obs)
        Mockito.verify(obs).onChanged(moviesDummy)
    }
}