package com.dicoding.eigacatalogue

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.eigacatalogue.source.MovieRepository
import com.dicoding.eigacatalogue.viewmodels.DetailViewModel
import com.dicoding.eigacatalogue.viewmodels.MovieViewModel
import com.dicoding.eigacatalogue.viewmodels.TVShowViewModel

class ViewModelFactory private constructor(private val movieRepository: MovieRepository)
    : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                instance = this
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                return MovieViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(TVShowViewModel::class.java) -> {
                return TVShowViewModel(movieRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}