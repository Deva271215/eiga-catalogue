package com.dicoding.eigacatalogue.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.eigacatalogue.DummyEntries
import com.dicoding.eigacatalogue.LiveDataTestKit
import com.dicoding.eigacatalogue.source.remote.RemoteDataSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteDataSource = Mockito.mock(RemoteDataSource::class.java)
    private val movieRepository = UnrealMovieRepositoryTest(remoteDataSource)
    private val remoteMoviesDummy = DummyEntries.getRemoteMovieDummy()
    private val remoteMoviesDummyId = remoteMoviesDummy[0].id
    private val remoteTVShowsDummy = DummyEntries.getRemoteTVShowDummy()
    private val remoteTVShowsDummyId = remoteTVShowsDummy[0].id

    @Test
    fun fetchMovies() {
        Mockito.doAnswer {
            (it.arguments[0] as RemoteDataSource.MovieCallback)
                .onMoviesFetched(remoteMoviesDummy)
            null
        }.`when`(remoteDataSource).fetchMovies(any())
        val movies = LiveDataTestKit.getValue(movieRepository.fetchMovies())
        verify(remoteDataSource).fetchMovies(any())
        assertNotNull(movies)
        assertEquals(remoteMoviesDummy.size.toLong(), movies.size.toLong())
    }

    @Test
    fun fetchTVShows() {
        Mockito.doAnswer {
            (it.arguments[0] as RemoteDataSource.TVShowCallback)
                .onTVShowFetched(remoteTVShowsDummy)
            null
        }.`when`(remoteDataSource).fetchTVShows(any())
        val tvShows = LiveDataTestKit.getValue(movieRepository.fetchTVShows())
        verify(remoteDataSource).fetchTVShows(any())
        assertNotNull(tvShows)
        assertEquals(remoteTVShowsDummy.size.toLong(), tvShows.size.toLong())
    }

    @Test
    fun findMovie() {
        Mockito.doAnswer {
            (it.arguments[1] as RemoteDataSource.DetailMovieCallback)
                .onMovieFound(remoteMoviesDummy[0])
        }.`when`(remoteDataSource).findMovie(eq(remoteMoviesDummyId), any())

        val movie = LiveDataTestKit.getValue(movieRepository.findMovie(remoteMoviesDummyId))
        verify(remoteDataSource).findMovie(eq(remoteMoviesDummyId), any())
        assertNotNull(movie)
        assertNotNull(movie.title)
        assertEquals(remoteMoviesDummy[0].title, movie.title)
    }

    @Test
    fun findTVShow() {
        Mockito.doAnswer {
            (it.arguments[1] as RemoteDataSource.DetailTVShowCallback)
                .onDetailTVShowFound(remoteTVShowsDummy[0])
        }.`when`(remoteDataSource).findTVShow(eq(remoteTVShowsDummyId), any())

        val tvShow = LiveDataTestKit.getValue(movieRepository.findTVShow(remoteTVShowsDummyId))
        verify(remoteDataSource).findTVShow(eq(remoteTVShowsDummyId), any())
        assertNotNull(tvShow)
        assertNotNull(tvShow.title)
        assertEquals(remoteTVShowsDummy[0].title, tvShow.title)
    }
}