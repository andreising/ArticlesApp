package com.andreisingeleytsev.articlesapp.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andreisingeleytsev.articlesapp.ui.screens.add_article_screen.AddArticlesScreen
import com.andreisingeleytsev.articlesapp.ui.screens.article_screen.ArticleScreen
import com.andreisingeleytsev.articlesapp.ui.screens.articles_list_screen.ArticlesListScreen
import com.andreisingeleytsev.articlesapp.ui.screens.favourites_list_screen.FavouritesListScreen
import com.andreisingeleytsev.articlesapp.ui.utils.Routes


@Composable
fun ArticleAppMainNavigationGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = Routes.ARTICLES_LIST_SCREEN) {
        composable(Routes.ARTICLES_LIST_SCREEN) {
            ArticlesListScreen(navHostController)
        }
        composable(Routes.ADD_ARTICLES_SCREEN+"/{id}") {
            AddArticlesScreen(navHostController)
        }
        composable(Routes.FAVOURITES_LIST_SCREEN) {
            FavouritesListScreen(navHostController)
        }
        composable(Routes.ARTICLE_SCREEN+"/{id}") {
            ArticleScreen(navHostController)
        }

    }
}
