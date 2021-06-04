package com.dicoding.eigacatalogue.source.local

import com.dicoding.eigacatalogue.MovieEntity

class LocalDataSource private constructor(private val favoriteDao: FavoriteDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null
        fun getInstance(favoriteDao: FavoriteDao): LocalDataSource = INSTANCE ?: synchronized(this) {
            INSTANCE ?: LocalDataSource(favoriteDao)
        }
    }

    fun insertFavorite(f: MovieEntity) = favoriteDao.insertFavorite(f)
}