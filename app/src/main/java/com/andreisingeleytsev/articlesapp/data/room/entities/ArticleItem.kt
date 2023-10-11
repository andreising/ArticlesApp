package com.andreisingeleytsev.articlesapp.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_item")
data class ArticleItem(
    @PrimaryKey
    val id: Int? = null,
    val name: String,
    val title: String,
    val imgId: Int?,
    val isFavourite: Boolean
)
