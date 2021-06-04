package com.dicoding.eigacatalogue

import android.content.Context
import com.dicoding.eigacatalogue.source.MovieRepository
import com.dicoding.eigacatalogue.source.local.LocalDataSource
import com.dicoding.eigacatalogue.source.local.LocalDatabase
import com.dicoding.eigacatalogue.source.remote.RemoteDataSource
import java.util.concurrent.Executors

object Injection {
    fun provideRepository(context: Context): MovieRepository {
        val database = LocalDatabase.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(database.favoriteDao())
        val executorService = Executors.newSingleThreadExecutor()
        return MovieRepository.getInstance(RemoteDataSource, localDataSource, executorService)
    }
}