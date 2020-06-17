package com.nitinm.jet2articles.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nitinm.jet2articles.data.model.ArticleDataEntity

@Dao
interface ArticleDao {

    @Query("SELECT Count(*) FROM articles_table")
    fun getNumberOfData(): Int

    @Query("SELECT articles FROM articles_table WHERE id = :pageNumber")
    fun getListByPageNumber(pageNumber: Int): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(articleDataEntity: ArticleDataEntity)
}