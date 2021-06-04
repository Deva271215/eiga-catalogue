package com.dicoding.eigacatalogue.source.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

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
