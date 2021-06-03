package com.dicoding.eigacatalogue

import com.dicoding.eigacatalogue.source.MovieRepository
import com.dicoding.eigacatalogue.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(): MovieRepository {
        return MovieRepository.getInstance(RemoteDataSource)
    }
}