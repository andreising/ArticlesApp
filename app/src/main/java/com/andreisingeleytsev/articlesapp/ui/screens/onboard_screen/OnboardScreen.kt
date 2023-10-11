package com.andreisingeleytsev.articlesapp.ui.screens.onboard_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.andreisingeleytsev.articlesapp.R
import com.andreisingeleytsev.articlesapp.ui.theme.Primary
import com.andreisingeleytsev.articlesapp.ui.utils.ArticlesAppFonts

@Composable
fun OnBoardScreen(onClick: () -> Unit, viewModel: OnBoardScreenViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = stringResource(R.string.onboard_title), style = TextStyle(
                color = Color.Black, fontSize = 16.sp, fontFamily = ArticlesAppFonts.font
            ), modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = {
                onClick.invoke()
                viewModel.saveOnBoardingState()
            }, colors = ButtonDefaults.buttonColors(
                containerColor = Primary
            ), shape = RoundedCornerShape(16.dp), modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.start),
                modifier = Modifier.padding(20.dp),
                style = TextStyle(
                    color = Color.White, fontSize = 14.sp, fontFamily = ArticlesAppFonts.font
                )
            )
        }
    }
}