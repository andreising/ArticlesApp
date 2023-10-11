package com.andreisingeleytsev.articlesapp.data.room.repository


import com.andreisingeleytsev.articlesapp.data.room.entities.ArticleItem
import kotlinx.coroutines.flow.Flow

interface ArticleItemRepository {
    suspend fun insertItem(item: ArticleItem)
    suspend fun deleteItem(item: ArticleItem)
    suspend fun getArticleItem(id: Int): ArticleItem
    fun getAllArticles(): Flow<List<ArticleItem>>
    fun searchArticles(searchText: String): Flow<List<ArticleItem>>
    fun getFavouriteArticles(): Flow<List<ArticleItem>>
    fun searchFavouriteArticles(searchText: String): Flow<List<ArticleItem>>
}