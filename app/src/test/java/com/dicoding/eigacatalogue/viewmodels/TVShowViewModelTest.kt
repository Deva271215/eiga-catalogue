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
class TVShowViewModelTest {
    private lateinit var viewModel: TVShowViewModel

    @get:Rule
    var instantTaskExtRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var obs: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = TVShowViewModel(movieRepository)
    }

    @Test
    fun getTVShow() {
        val tvShowDummy = DummyEntries.getTVShowDummy()
        val tvShows = MutableLiveData<List<MovieEntity>>()
        tvShows.value = tvShowDummy

        Mockito.`when`(movieRepository.fetchTVShows()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getTVShow().value
        Mockito.verify(movieRepository).fetchTVShows()
        Assert.assertNotNull(tvShowEntities)
        Assert.assertEquals(1, tvShowEntities?.size)

        viewModel.getTVShow().observeForever(obs)
        Mockito.verify(obs).onChanged(tvShowDummy)
    }
}