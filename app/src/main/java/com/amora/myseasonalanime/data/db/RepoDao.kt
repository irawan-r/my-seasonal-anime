package com.amora.myseasonalanime.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amora.myseasonalanime.data.model.popular.Anime
import com.amora.myseasonalanime.data.model.search.AnimeSearch

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertPopularAnime(repos: List<Anime>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertSearchAnime(repos: List<AnimeSearch>)

    @Query("SELECT * FROM AnimeSearch WHERE " +
            "title LIKE :queryString ")
    fun searchAnime(queryString: String): PagingSource<Int, AnimeSearch>

    @Query("SELECT * FROM Anime")
    fun getPagingAnime(): PagingSource<Int, Anime>

    @Query("DELETE FROM Anime")
    suspend fun clearPopularAnime()

    @Query("DELETE FROM AnimeSearch")
    suspend fun clearSearchAnime()
}