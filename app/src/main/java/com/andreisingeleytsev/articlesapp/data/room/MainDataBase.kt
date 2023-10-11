package com.andreisingeleytsev.articlesapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andreisingeleytsev.articlesapp.data.room.dao.ArticleItemDao
import com.andreisingeleytsev.articlesapp.data.room.entities.ArticleItem

@Database(
    entities = [ArticleItem::class],
    version = 1
)
abstract class MainDataBase: RoomDatabase() {
    abstract val articleItemDao: ArticleItemDao
}