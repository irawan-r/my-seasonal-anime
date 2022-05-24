package com.amora.myseasonalanime.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.amora.myseasonalanime.data.model.popular.Anime
import com.amora.myseasonalanime.data.model.search.AnimeSearch

@Database(
    entities = [
        Anime::class,
        RemoteKeys::class,
        AnimeSearch::class
    ],
    version = 1
)
@TypeConverters(Converter::class)
abstract class RepoDatabase : RoomDatabase() {

    abstract fun animeDao(): RepoDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {

        @Volatile
        private var INSTANCE: RepoDatabase? = null

        fun getInstance(context: Context): RepoDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context)
                        .also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                RepoDatabase::class.java,
                "Anime.db"
            )
                .addTypeConverter(Converter())
                .fallbackToDestructiveMigration()
                .build()

    }
}