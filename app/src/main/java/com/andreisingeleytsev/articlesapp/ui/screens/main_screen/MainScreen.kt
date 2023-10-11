package com.andreisingeleytsev.articlesapp.ui.screens.main_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.andreisingeleytsev.articlesapp.R
import com.andreisingeleytsev.articlesapp.ui.menu.ArticlesBottomNavigationMenu
import com.andreisingeleytsev.articlesapp.ui.navigation.ArticleAppMainNavigationGraph
import com.andreisingeleytsev.articlesapp.ui.screens.add_article_screen.AddArticlesScreenEvent
import com.andreisingeleytsev.articlesapp.ui.screens.add_article_screen.AddArticlesViewModel
import com.andreisingeleytsev.articlesapp.ui.theme.Primary
import com.andreisingeleytsev.articlesapp.ui.utils.ArticlesAppFonts
import com.andreisingeleytsev.articlesapp.ui.utils.Routes
import com.andreisingeleytsev.articlesapp.ui.utils.UIEvents

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navHostController = rememberNavController()
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            ArticlesBottomNavigationMenu(currentRoute = currentRoute) { currentRoute ->
                navHostController.navigate(currentRoute)
            }
        }, topBar = {
            TopAppBar(title = {
                Text(
                    text = stringResource(
                        id = when (currentRoute) {
                            Routes.ARTICLE_SCREEN +"/{id}" -> {
                                R.string.article
                            }

                            Routes.ADD_ARTICLES_SCREEN +"/{id}"-> {
                                R.string.write_article
                            }

                            Routes.FAVOURITES_LIST_SCREEN -> {
                                R.string.favourites
                            }

                            Routes.ARTICLES_LIST_SCREEN -> {
                                R.string.written_articles
                            }

                            else -> {
                                R.string.app_name
                            }
                        }
                    ).uppercase(), style = TextStyle(
                        color = Primary,
                        fontSize = 16.sp,
                        fontFamily = ArticlesAppFonts.font,
                        textAlign = TextAlign.Center
                    ), modifier = Modifier.fillMaxWidth()
                )
            })
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            ArticleAppMainNavigationGraph(navHostController = navHostController)
        }
    }
}