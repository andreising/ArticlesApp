package com.andreisingeleytsev.articlesapp.data.room.repository.implementations

import com.andreisingeleytsev.articlesapp.data.room.repository.ArticleItemRepository
import com.andreisingeleytsev.articlesapp.data.room.dao.ArticleItemDao
import com.andreisingeleytsev.articlesapp.data.room.entities.ArticleItem
import kotlinx.coroutines.flow.Flow

class ArticleItemRepositoryImpl(
    private val dao: ArticleItemDao
) : ArticleItemRepository {
    override suspend fun insertItem(item: ArticleItem) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: ArticleItem) {
        dao.deleteItem(item)
    }

    override suspend fun getArticleItem(id: Int): ArticleItem {
        return dao.getArticleItem(id)
    }

    override fun getAllArticles(): Flow<List<ArticleItem>> {
        return dao.getAllArticles()
    }

    override fun searchArticles(searchText: String): Flow<List<ArticleItem>> {
        return  dao.searchArticles(searchText)
    }

    override fun getFavouriteArticles(): Flow<List<ArticleItem>> {
        return dao.getFavouriteArticles(true)
    }

    override fun searchFavouriteArticles(searchText: String): Flow<List<ArticleItem>> {
        return dao.searchFavouriteArticles(searchText, true)
    }


}