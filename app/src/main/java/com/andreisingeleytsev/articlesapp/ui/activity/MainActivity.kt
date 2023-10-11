package com.andreisingeleytsev.articlesapp.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.andreisingeleytsev.articlesapp.ui.screens.main_screen.MainScreen
import com.andreisingeleytsev.articlesapp.ui.screens.onboard_screen.OnBoardScreen
import com.andreisingeleytsev.articlesapp.ui.theme.MainColor
import com.andreisingeleytsev.articlesapp.ui.utils.Routes
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !viewModel.isLoading.value
            }
        }
        setContent {
            Surface(modifier = Modifier.fillMaxSize(), color = MainColor) {
                when (viewModel.startDestination.value) {
                    Routes.MAIN_SCREEN -> {
                        MainScreen()
                    }

                    Routes.ONBOARDING_SCREEN -> {
                        OnBoardScreen({
                            viewModel.onBoardFinished()
                        })
                    }

                    else -> {

                    }
                }
            }
        }
    }
}