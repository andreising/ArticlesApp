package com.andreisingeleytsev.articlesapp.ui.screens.article_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreisingeleytsev.articlesapp.data.room.entities.ArticleItem
import com.andreisingeleytsev.articlesapp.data.room.repository.ArticleItemRepository
import com.andreisingeleytsev.articlesapp.ui.screens.add_article_screen.AddArticlesScreenEvent
import com.andreisingeleytsev.articlesapp.ui.utils.Routes
import com.andreisingeleytsev.articlesapp.ui.utils.UIEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleScreenViewModel@Inject constructor(
    private val repository: ArticleItemRepository,
    savedStateHandle: SavedStateHandle?
): ViewModel() {
    private val _uiEvent = Channel<UIEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ArticleScreenEvent){
        when(event){
            is ArticleScreenEvent.OnEditPressed-> {
                sendUIEvent(UIEvents.Navigate(Routes.ADD_ARTICLES_SCREEN+"/${article!!.id}"))
            }
            is ArticleScreenEvent.OnFavouritesPressed-> {
                viewModelScope.launch {
                    _isFavourite.value = !_isFavourite.value
                    repository.insertItem(article!!.copy(isFavourite = _isFavourite.value))
                }
            }
        }
    }
    private fun sendUIEvent(event: UIEvents){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    var article: ArticleItem? = null

    private val _name = mutableStateOf("")
    val name:State<String> = _name

    private val _title = mutableStateOf("")
    val title:State<String> = _title

    private val _isFavourite = mutableStateOf(false)
    val isFavourite:State<Boolean> = _isFavourite

    val imageId = mutableStateOf<Int?>(null)

    init {
        viewModelScope.launch {
            val id = savedStateHandle?.get<String>("id")?.toIntOrNull() ?: -1
            if (id!=-1) article = repository.getArticleItem(id)
            if (article!=null) {
                _name.value = article!!.name
                _title.value = article!!.title
                imageId.value = article!!.imgId
                _isFavourite.value = article!!.isFavourite
            }
        }
    }
}