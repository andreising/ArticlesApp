package com.andreisingeleytsev.articlesapp.ui.screens.articles_list_screen

import com.andreisingeleytsev.articlesapp.ui.screens.article_screen.ArticleScreenEvent

sealed class ArticlesListScreenEvent{
    data class OnItemChose(val id: Int): ArticlesListScreenEvent()
    data class OnSearchTextChanged(val string: String): ArticlesListScreenEvent()
}
