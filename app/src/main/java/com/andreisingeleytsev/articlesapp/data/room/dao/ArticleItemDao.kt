package com.andreisingeleytsev.articlesapp.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andreisingeleytsev.articlesapp.data.room.entities.ArticleItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ArticleItem)

    @Delete
    suspend fun deleteItem(item: ArticleItem)

    @Query("SELECT * FROM article_item WHERE id IS :id")
    suspend fun getArticleItem(id: Int): ArticleItem

    @Query("SELECT * FROM article_item")
    fun getAllArticles(): Flow<List<ArticleItem>>

    @Query("SELECT * FROM article_item WHERE name LIKE '%' || :searchText || '%' OR title LIKE '%' || :searchText || '%'")
    fun searchArticles(searchText: String): Flow<List<ArticleItem>>

    @Query("SELECT * FROM article_item WHERE isFavourite IS :bool")
    fun getFavouriteArticles(bool: Boolean): Flow<List<ArticleItem>>

    @Query("SELECT * FROM article_item WHERE (name LIKE '%' || :searchText || '%' OR title LIKE '%' || :searchText || '%') AND isFavourite IS :bool")
    fun searchFavouriteArticles(searchText: String, bool: Boolean): Flow<List<ArticleItem>>
}