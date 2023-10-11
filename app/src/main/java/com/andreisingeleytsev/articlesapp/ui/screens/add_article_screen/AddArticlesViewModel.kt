package com.andreisingeleytsev.articlesapp.ui.screens.add_article_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreisingeleytsev.articlesapp.data.room.entities.ArticleItem
import com.andreisingeleytsev.articlesapp.data.room.repository.ArticleItemRepository
import com.andreisingeleytsev.articlesapp.ui.utils.CoverList
import com.andreisingeleytsev.articlesapp.ui.utils.Routes
import com.andreisingeleytsev.articlesapp.ui.utils.UIEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddArticlesViewModel@Inject constructor(
    private val repository: ArticleItemRepository,
    savedStateHandle: SavedStateHandle?
): ViewModel() {
    private val _uiEvent = Channel<UIEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: AddArticlesScreenEvent){
        when(event){
            is AddArticlesScreenEvent.OnSaveArticle -> {
                viewModelScope.launch {
                    if (_title.value.isNotEmpty()&&_name.value.isNotEmpty()){
                        article = ArticleItem(
                            id = if (article!=null) article!!.id
                            else null,
                            name = _name.value,
                            title = _title.value,
                            imgId = CoverList.list[imageId.value!!],
                            isFavourite = if (article!=null) article!!.isFavourite
                            else false
                        )
                        repository.insertItem(article!!)
                        sendUIEvent(UIEvents.OnBack)
                        sendUIEvent(UIEvents.Navigate(Routes.ARTICLES_LIST_SCREEN))
                    }
                }
            }
            is AddArticlesScreenEvent.OnTitleChange -> {
                _title.value = event.text
            }
            is AddArticlesScreenEvent.OnNameChange -> {
                _name.value = event.text
            }
        }
    }
    private fun sendUIEvent(event: UIEvents){
        Log.d("tag", "check")
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    private var article: ArticleItem? = null

    private val _name = mutableStateOf("")
    val name: State<String> = _name

    private val _title = mutableStateOf("")
    val title: State<String> = _title

    var imageId = mutableStateOf<Int?>(null)

    init {
        viewModelScope.launch {
            val id = savedStateHandle?.get<String>("id")?.toIntOrNull() ?: -1
            if (id!=-1) article = repository.getArticleItem(id)
            if (article!=null) {
                _name.value = article!!.name
                _title.value = article!!.title
                imageId.value = article!!.imgId
                sendUIEvent(UIEvents.ScrollPager(imageId.value))
            }
        }
    }
}