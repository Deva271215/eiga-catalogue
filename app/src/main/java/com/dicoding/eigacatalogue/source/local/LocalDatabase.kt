package com.dicoding.eigacatalogue.source.local

import android.content.Context
import android.graphics.Movie
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.eigacatalogue.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class LocalDatabase: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                LocalDatabase::class.java,
                "eiga_catalogue_db"
            ).build()
        }
    }
}
