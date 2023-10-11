package com.andreisingeleytsev.articlesapp.ui.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.andreisingeleytsev.articlesapp.ui.theme.Primary

@Composable
fun ArticlesBottomNavigationMenu(currentRoute: String?, onNavigate: (String)->Unit) {
    val listItems = listOf(
        ArticlesAppBottomNavigationItem.HomeScreen,
        ArticlesAppBottomNavigationItem.ShowArticleScreen,
        ArticlesAppBottomNavigationItem.AddArticleScreen,
        ArticlesAppBottomNavigationItem.FavouritesScreen

    )
    BottomAppBar(
        containerColor = Color.White
    ) {
        listItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute==item.route,
                onClick = {
                    onNavigate(item.route)
                },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.iconId),
                            contentDescription = "bottom_icon"
                        )
                    }

                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Primary,
                    unselectedIconColor = Color.Gray,
                    indicatorColor = Color.White
                )
            )

        }
    }
}