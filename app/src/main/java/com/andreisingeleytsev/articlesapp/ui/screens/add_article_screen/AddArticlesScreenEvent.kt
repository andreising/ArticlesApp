package com.andreisingeleytsev.articlesapp.ui.screens.add_article_screen

sealed class AddArticlesScreenEvent{
    data class OnTitleChange(val text: String): AddArticlesScreenEvent()
    data class OnNameChange(val text: String): AddArticlesScreenEvent()
    object OnSaveArticle: AddArticlesScreenEvent()
}
