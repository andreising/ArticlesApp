package com.andreisingeleytsev.articlesapp.ui.screens.add_article_screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.andreisingeleytsev.articlesapp.R
import com.andreisingeleytsev.articlesapp.ui.theme.MainColor
import com.andreisingeleytsev.articlesapp.ui.theme.Primary
import com.andreisingeleytsev.articlesapp.ui.utils.ArticlesAppFonts
import com.andreisingeleytsev.articlesapp.ui.utils.CoverList
import com.andreisingeleytsev.articlesapp.ui.utils.Routes
import com.andreisingeleytsev.articlesapp.ui.utils.UIEvents

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddArticlesScreen(
    navHostController: NavHostController,
    viewModel: AddArticlesViewModel = hiltViewModel()
) {
    val coverList = CoverList.list
    val pagerState = rememberPagerState {
        coverList.size
    }
    viewModel.imageId.value = pagerState.currentPage
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
            when (it) {
                is UIEvents.ScrollPager -> {
                    pagerState.scrollToPage(coverList.indexOf(viewModel.imageId.value))
                }

                is UIEvents.Navigate -> {
                    navHostController.navigate(it.route)
                }

                is UIEvents.OnBack -> {
                    navHostController.popBackStack()
                }

                else -> {}
            }
        }
    }
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(), shape = RoundedCornerShape(14.dp), colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .padding(14.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = stringResource(R.string.title), style = TextStyle(
                        color = Color.LightGray,
                        fontSize = 12.sp,
                        fontFamily = ArticlesAppFonts.font
                    )
                )
                IconButton(
                    onClick = { viewModel.onEvent(AddArticlesScreenEvent.OnSaveArticle) },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Primary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f),
                colors = CardDefaults.cardColors(
                    containerColor = MainColor
                ),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, Color.LightGray.copy(0.7F))
            ) {

                TextField(
                    value = viewModel.name.value,
                    onValueChange = {
                        viewModel.onEvent(AddArticlesScreenEvent.OnNameChange(it))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontFamily = ArticlesAppFonts.font,
                        fontSize = 14.sp
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ), singleLine = true
                )
            }
            Text(
                text = stringResource(R.string.text), style = TextStyle(
                    color = Color.LightGray,
                    fontSize = 12.sp,
                    fontFamily = ArticlesAppFonts.font
                ), modifier = Modifier.weight(1f)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(10f),
                colors = CardDefaults.cardColors(
                    containerColor = MainColor
                ),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, Color.LightGray.copy(0.7F))
            ) {

                TextField(
                    value = viewModel.title.value,
                    onValueChange = {
                        viewModel.onEvent(AddArticlesScreenEvent.OnTitleChange(it))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(
                        color = Color.LightGray,
                        fontFamily = ArticlesAppFonts.font,
                        fontSize = 14.sp
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ), singleLine = false
                )
            }
            Text(
                text = stringResource(R.string.select_img), style = TextStyle(
                    color = Color.LightGray,
                    fontSize = 12.sp,
                    fontFamily = ArticlesAppFonts.font
                ), modifier = Modifier.weight(1f)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(7f),
                colors = CardDefaults.cardColors(
                    containerColor = MainColor
                ),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, Color.LightGray.copy(0.7F))
            ) {
                HorizontalPager(state = pagerState) {
                    if (coverList[it] != null) Image(
                        painter = painterResource(id = coverList[it]!!),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    ) else Text(
                        modifier = Modifier.fillMaxSize(),
                        text = stringResource(R.string.no_image),
                        style = TextStyle(
                            color = Color.LightGray,
                            fontSize = 12.sp,
                            fontFamily = ArticlesAppFonts.font, textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}