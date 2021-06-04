package com.dicoding.eigacatalogue.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.eigacatalogue.MovieEntity
import com.dicoding.eigacatalogue.source.local.LocalDataSource
import com.dicoding.eigacatalogue.source.local.LocalDatabase
import com.dicoding.eigacatalogue.source.remote.RemoteDataSource
import com.dicoding.eigacatalogue.source.remote.response.CreatedBy
import com.dicoding.eigacatalogue.source.remote.response.DetailMovieResponse
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.collections.ArrayList

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val executorService: ExecutorService
): MovieDataSource {
    companion object {
        @Volatile
        private var instance: MovieRepository? = null
        fun getInstance(
            remoteData: RemoteDataSource,
            localDataSource: LocalDataSource,
            executorService: ExecutorService
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, localDataSource, executorService).apply { instance = this }
            }
    }

    override fun fetchMovies(): LiveData<List<MovieEntity>> {
        val movies = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.fetchMovies(object: RemoteDataSource.MovieCallback {
            override fun onMoviesFetched(movieResponses: List<DetailMovieResponse>?) {
                val response = ArrayList<MovieEntity>()
                for (m in movieResponses!!) {
                    var creators = m.createdBy?.joinToString {"${it.name}"}
                    var genres = m.genres?.joinToString { "${it.name}" }
                    val movie = MovieEntity(
                        id = m.id,
                        title = m.title,
                        release = m.release,
                        image = "https://image.tmdb.org/t/p/w500" + m.image,
                    )
                    response.add(movie)
                }
                movies.postValue(response)
            }
        })

        return movies
    }

    override fun findMovie(id: Int?): LiveData<MovieEntity> {
        val detail = MutableLiveData<MovieEntity>()
        remoteDataSource.findMovie(id, object: RemoteDataSource.DetailMovieCallback {
            override fun onMovieFound(movieResponses: DetailMovieResponse) {
                var creators = movieResponses.createdBy?.joinToString {"${it.name}"}
                var genres = movieResponses.genres?.joinToString { "${it.name}" }
                lateinit var movie: MovieEntity
                if (movieResponses.id == id) {
                    movie = MovieEntity(
                        id = movieResponses.id,
                        title = movieResponses.title,
                        tagLine = if(movieResponses.tagLine.isNullOrEmpty()) "Incredible movie" else movieResponses.tagLine,
                        release = movieResponses.release,
                        overview = movieResponses.overview,
                        image = "https://image.tmdb.org/t/p/w500" + movieResponses.image,
                        score = movieResponses.popularity,
                        genre = if(genres.isNullOrEmpty()) "-" else genres,
                        creator = if (creators.isNullOrEmpty()) "-" else creators
                    )
                }
                detail.postValue(movie)
            }
        })

        return detail
    }

    override fun fetchTVShows(): LiveData<List<MovieEntity>> {
        val tvShows = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.fetchTVShows(object: RemoteDataSource.TVShowCallback {
            override fun onTVShowFetched(tvShowResponse: List<DetailMovieResponse>?) {
                val response = ArrayList<MovieEntity>()
                for (tvs in tvShowResponse!!) {
                    var creators = tvs.createdBy?.joinToString {"${it.name}"}
                    var genres = tvs.genres?.joinToString { "${it.name}" }
                    val tvShow = MovieEntity(
                            id = tvs.id,
                            title = tvs.name,
                            release = tvs.firstAirDate,
                            image = "https://image.tmdb.org/t/p/w500" + tvs.image,
                    )
                    response.add(tvShow)
                }
                tvShows.postValue(response)
            }
        })
        return tvShows
    }

    override fun findTVShow(id: Int?): LiveData<MovieEntity> {
        val tvShow = MutableLiveData<MovieEntity>()
        remoteDataSource.findTVShow(id, object: RemoteDataSource.DetailTVShowCallback {
            override fun onDetailTVShowFound(tvShowResponse: DetailMovieResponse) {
                var creators = tvShowResponse.createdBy?.joinToString {"${it.name}"}
                var genres = tvShowResponse.genres?.joinToString { "${it.name}" }
                lateinit var tv: MovieEntity
                if (tvShowResponse.id == id) {
                    tv = MovieEntity(
                            id = tvShowResponse.id,
                            title = tvShowResponse.name,
                            tagLine = if(tvShowResponse.tagLine.isNullOrEmpty()) "Incredible movie" else tvShowResponse.tagLine,
                            release = tvShowResponse.firstAirDate,
                            overview = tvShowResponse.overview,
                            image = "https://image.tmdb.org/t/p/w500" + tvShowResponse.image,
                            score = tvShowResponse.popularity,
                            genre = if(genres.isNullOrEmpty()) "-" else genres,
                            creator = if (creators.isNullOrEmpty()) "-" else creators
                    )
                }
                tvShow.postValue(tv)
            }

        })

        return tvShow
    }

    override fun insertFavorite(f: MovieEntity) = executorService.execute {
        f.isFavorited = true
        localDataSource.insertFavorite(f)
    }

    override fun removeFavorite(f: MovieEntity) = executorService.execute {
        f.isFavorited = false
        localDataSource.removeFavorite(f)
    }

}