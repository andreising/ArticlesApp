package com.andreisingeleytsev.articlesapp.ui.screens.article_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.andreisingeleytsev.articlesapp.R
import com.andreisingeleytsev.articlesapp.ui.theme.Primary
import com.andreisingeleytsev.articlesapp.ui.utils.ArticlesAppFonts
import com.andreisingeleytsev.articlesapp.ui.utils.UIEvents

@Composable
fun ArticleScreen(navHostController: NavHostController, viewModel: ArticleScreenViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
            when (it) {

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
    if (viewModel.article != null) Column(modifier = Modifier.fillMaxSize()) {
        if (viewModel.imageId.value!=null) Image(
            painter = painterResource(id = viewModel.imageId.value!!),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .weight(4f),
            contentScale = ContentScale.Crop
        )
        Text(
            text = viewModel.name.value, maxLines = 2, style = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontFamily = ArticlesAppFonts.font,
                fontWeight = FontWeight.Bold
            ), modifier = Modifier.padding(12.dp).weight(1f)
        )
        Text(
            text = viewModel.title.value, style = TextStyle(
                color = Color.Gray,
                fontSize = 14.sp,
                fontFamily = ArticlesAppFonts.font,
            ), modifier = Modifier.padding(12.dp).weight(5f)
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(1f)
        ) {
            Button(
                onClick = {
                          viewModel.onEvent(ArticleScreenEvent.OnEditPressed)
                }, modifier = Modifier.weight(7f), colors = ButtonDefaults.buttonColors(
                    containerColor = Primary
                )
            ) {
                Text(
                    text = stringResource(R.string.edit_article), style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                        fontFamily = ArticlesAppFonts.font,
                    )
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Button(
                onClick = {
                    viewModel.onEvent(ArticleScreenEvent.OnFavouritesPressed)
                }, modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(
                    containerColor = if (viewModel.isFavourite.value) Primary
                    else Color.White
                ), shape = RoundedCornerShape(16.dp), contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_favourites),
                    contentDescription = null,
                    tint = if (viewModel.isFavourite.value) Color.White
                    else Primary
                )
            }

        }
    } else Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "Choose an article", style = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontFamily = ArticlesAppFonts.font,
                fontWeight = FontWeight.Bold
            )
        )
    }
}