package com.dicoding.eigacatalogue.source.remote

import android.util.Log
import com.dicoding.eigacatalogue.IdlingResource
import com.dicoding.eigacatalogue.api.ConfigAPI
import com.dicoding.eigacatalogue.source.remote.response.DetailMovieResponse
import com.dicoding.eigacatalogue.source.remote.response.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RemoteDataSource {
    fun fetchMovies(callback: MovieCallback) {
        val client = ConfigAPI.callRemoteService().getPopularMovie()
        IdlingResource.inc()
        client.enqueue(object: Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if(response.isSuccessful) {
                    val movies = response.body()?.results
                    callback.onMoviesFetched(movies)
                } else {
                    Log.d("Error: ", "Error on fetching movies")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("Error: ", "Failed on fetching movies " + t.message)
            }

        })
        IdlingResource.dec()
    }

    fun findMovie(id: Int?, callback: DetailMovieCallback) {
        val client = ConfigAPI.callRemoteService().getDetailMovie(id)
        IdlingResource.inc()
        client.enqueue(object: Callback<DetailMovieResponse> {
            override fun onResponse(
                call: Call<DetailMovieResponse>,
                response: Response<DetailMovieResponse>
            ) {
                if(response.isSuccessful) {
                    val detailMovie = response.body()
                    if (detailMovie != null) {
                        callback.onMovieFound(detailMovie)
                    }
                } else {
                    Log.d("Error: ", "Error on finding movie")
                }
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.d("Error: ", "Error on finding movie")
            }

        })
        IdlingResource.dec()
    }

    fun fetchTVShows(callback: TVShowCallback) {
        val client = ConfigAPI.callRemoteService().getPopularTVShows()
        IdlingResource.inc()
        client.enqueue(object: Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if(response.isSuccessful) {
                    val tvShows = response.body()?.results
                    callback.onTVShowFetched(tvShows)
                } else {
                    Log.d("Error: ", "Error on finding TV shows")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("Error: ", "Error on finding TV shows")
            }

        })
        IdlingResource.dec()
    }

    fun findTVShow(id: Int?, callback: DetailTVShowCallback) {
        val client = ConfigAPI.callRemoteService().getDetailTVShow(id)
        IdlingResource.inc()
        client.enqueue(object: Callback<DetailMovieResponse> {
            override fun onResponse(call: Call<DetailMovieResponse>, response: Response<DetailMovieResponse>) {
                if (response.isSuccessful) {
                    val tvShow = response.body()
                    if (tvShow != null) {
                        callback.onDetailTVShowFound(tvShow)
                    }
                } else {
                    Log.d("Error: ", "Error on finding TV show")
                }
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.d("Error: ", "Error on finding TV show")
            }

        })
        IdlingResource.dec()
    }

    interface MovieCallback {
        fun onMoviesFetched(movieResponses: List<DetailMovieResponse>?)
    }

    interface DetailMovieCallback {
        fun onMovieFound(movieResponses: DetailMovieResponse)
    }

    interface TVShowCallback {
        fun onTVShowFetched(tvShowResponse: List<DetailMovieResponse>?)
    }

    interface DetailTVShowCallback {
        fun onDetailTVShowFound(tvShowResponse: DetailMovieResponse)
    }
}