package com.dicoding.eigacatalogue.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.eigacatalogue.DummyEntries
import com.dicoding.eigacatalogue.MovieEntity
import com.dicoding.eigacatalogue.source.MovieRepository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    private val movieDummy = DummyEntries.getMovieDummy()[0]
    private val movieDummyId = movieDummy.id

    private val tvShowDummy = DummyEntries.getTVShowDummy()[0]
    private val tvShowDummyId = tvShowDummy.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieObs: Observer<MovieEntity>

    @Mock
    private lateinit var tvShowObs: Observer<MovieEntity>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailViewModel(movieRepository)
    }

    @Test
    fun getMovieDetail() {
        viewModel.setSelectedMovies(movieDummyId)
        val movie = MutableLiveData<MovieEntity>()
        movie.value = movieDummy

        Mockito.`when`(movieRepository.findMovie(movieDummyId)).thenReturn(movie)
        val movieEntity = viewModel.getMovie().value
        verify(movieRepository).findMovie(movieDummyId)

        assertNotNull(movieEntity)
        assertEquals(movieDummy.id, movieEntity?.id)
        assertEquals(movieDummy.title, movieEntity?.title)
        assertEquals(movieDummy.genre, movieEntity?.genre)
        assertEquals(movieDummy.release, movieEntity?.release)
        assertEquals(movieDummy.overview, movieEntity?.overview)
        assertEquals(movieDummy.image, movieEntity?.image)

        viewModel.getMovie().observeForever(movieObs)
        verify(movieObs).onChanged(movieDummy)
    }

    @Test
    fun getTVShowDetail() {
        viewModel.setSelectedMovies(tvShowDummyId)
        val tvShow = MutableLiveData<MovieEntity>()
        tvShow.value = tvShowDummy

        Mockito.`when`(movieRepository.findTVShow(tvShowDummyId)).thenReturn(tvShow)
        val tvShowEntity = viewModel.getTVShow().value
        verify(movieRepository).findTVShow(tvShowDummyId)

        assertNotNull(tvShowEntity)
        assertEquals(tvShowDummy.id, tvShowEntity?.id)
        assertEquals(tvShowDummy.title, tvShowEntity?.title)
        assertEquals(tvShowDummy.genre, tvShowEntity?.genre)
        assertEquals(tvShowDummy.release, tvShowEntity?.release)
        assertEquals(tvShowDummy.overview, tvShowEntity?.overview)
        assertEquals(tvShowDummy.genre, tvShowEntity?.genre)
        assertEquals(tvShowDummy.image, tvShowEntity?.image)

        viewModel.getTVShow().observeForever(tvShowObs)
        verify(tvShowObs).onChanged(tvShowDummy)
    }
}