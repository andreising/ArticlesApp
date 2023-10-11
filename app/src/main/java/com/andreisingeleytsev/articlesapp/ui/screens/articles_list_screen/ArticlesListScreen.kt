package com.andreisingeleytsev.articlesapp.ui.screens.articles_list_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.andreisingeleytsev.articlesapp.R
import com.andreisingeleytsev.articlesapp.data.room.entities.ArticleItem
import com.andreisingeleytsev.articlesapp.ui.utils.ArticlesAppFonts
import com.andreisingeleytsev.articlesapp.ui.utils.UIEvents

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesListScreen(navHostController: NavHostController, viewModel: ArticleListScreenViewModel = hiltViewModel()) {
    val text = remember {
        mutableStateOf("")
    }
    val flow = viewModel.articlesFlow.value.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
            when (it) {
                is UIEvents.Navigate -> {
                    navHostController.navigate(it.route)
                }

                else -> {}
            }
        }
    }
    Column(
        modifier = Modifier
            .padding(14.dp)
            .fillMaxSize()
    ) {
        SearchBar(
            query = text.value,
            onQueryChange = {
                text.value = it
                viewModel.onEvent(ArticlesListScreenEvent.OnSearchTextChanged(it))
            },
            onSearch = { },
            active = false,
            onActiveChange = { }, modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = stringResource(R.string.search).capitalize(Locale.current),
                    style = TextStyle(
                        color = Color.LightGray,
                        fontSize = 14.sp,
                        fontFamily = ArticlesAppFonts.font
                    )
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.LightGray
                )
            },
            shape = RoundedCornerShape(16.dp),
            colors = SearchBarDefaults.colors(
                containerColor = Color.White
            )
        ) {

        }
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = stringResource(R.string.written_articles).capitalize(Locale.current),
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = ArticlesAppFonts.font
            )
        )
        if (flow.value.isNotEmpty()) LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(flow.value) {
                ArticlesItem(articleItem = it)
            }
        } else Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = stringResource(R.string.no_articles),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = ArticlesAppFonts.font,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

@Composable
fun ArticlesItem(
    articleItem: ArticleItem,
    viewModel: ArticleListScreenViewModel = hiltViewModel()
) {
    Card(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
            .height(150.dp)
            .clickable {
                viewModel.onEvent(ArticlesListScreenEvent.OnItemChose(articleItem.id!!))
            }, colors = CardDefaults.cardColors(
            containerColor = Color.White
        ), shape = RoundedCornerShape(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Log.d("tag", articleItem.imgId.toString())
            if (articleItem.imgId != null) Image(
                painter = painterResource(id = articleItem.imgId),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = articleItem.name,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = ArticlesAppFonts.font
                    )
                )
                Text(
                    text = articleItem.title,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontFamily = ArticlesAppFonts.font
                    )
                )
            }
        }
    }
}