package com.andreisingeleytsev.articlesapp.ui.utils

sealed class UIEvents(){
    data class Navigate(val route: String): UIEvents()
    data class ScrollPager(val index: Int?): UIEvents()
    object OnBack: UIEvents()
}