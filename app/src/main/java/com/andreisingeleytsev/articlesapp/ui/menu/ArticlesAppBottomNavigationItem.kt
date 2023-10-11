package com.andreisingeleytsev.articlesapp.ui.menu

import com.andreisingeleytsev.articlesapp.R
import com.andreisingeleytsev.articlesapp.ui.utils.Routes

sealed class ArticlesAppBottomNavigationItem(val iconId: Int, val route: String) {
    object HomeScreen: ArticlesAppBottomNavigationItem( R.drawable.icon_home, Routes.ARTICLES_LIST_SCREEN)
    object ShowArticleScreen: ArticlesAppBottomNavigationItem( R.drawable.icon_search, Routes.ARTICLE_SCREEN+"/{id}")
    object AddArticleScreen: ArticlesAppBottomNavigationItem( R.drawable.icon_add, Routes.ADD_ARTICLES_SCREEN+"/{id}")
    object FavouritesScreen: ArticlesAppBottomNavigationItem( R.drawable.icon_favourites, Routes.FAVOURITES_LIST_SCREEN)
}
