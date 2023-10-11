package com.andreisingeleytsev.articlesapp.ui.screens.article_screen

sealed class ArticleScreenEvent{
    object OnEditPressed: ArticleScreenEvent()
    object OnFavouritesPressed: ArticleScreenEvent()
}
