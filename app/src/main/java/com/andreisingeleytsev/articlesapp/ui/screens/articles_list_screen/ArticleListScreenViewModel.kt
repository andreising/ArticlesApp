package com.andreisingeleytsev.articlesapp.ui.screens.articles_list_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreisingeleytsev.articlesapp.data.room.repository.ArticleItemRepository
import com.andreisingeleytsev.articlesapp.ui.screens.article_screen.ArticleScreenEvent
import com.andreisingeleytsev.articlesapp.ui.utils.Routes
import com.andreisingeleytsev.articlesapp.ui.utils.UIEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleListScreenViewModel@Inject constructor(
    private val repository: ArticleItemRepository
): ViewModel() {
    private val _uiEvent = Channel<UIEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ArticlesListScreenEvent){
        when(event){
            is ArticlesListScreenEvent.OnItemChose -> {
                sendUIEvent(UIEvents.Navigate(Routes.ARTICLE_SCREEN + "/${event.id}"))
            }
            is ArticlesListScreenEvent.OnSearchTextChanged ->{
                articlesFlow.value = repository.searchArticles(event.string)
            }
        }
    }
    private fun sendUIEvent(event: UIEvents){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    val articlesFlow = mutableStateOf( repository.getAllArticles())
}