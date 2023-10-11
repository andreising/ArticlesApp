package com.andreisingeleytsev.articlesapp.ui.activity

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreisingeleytsev.articlesapp.ui.utils.Routes
import com.andreisingeleytsev.articlesapp.data.datastore.DataStoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
): ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _startDestination: MutableState<String?> = mutableStateOf(null)
    val startDestination: State<String?> = _startDestination

    fun onBoardFinished(){
        _startDestination.value = Routes.MAIN_SCREEN
    }
    init {
        viewModelScope.launch {
            dataStoreRepository.readOnBoardingState().collect{completed ->
                if (completed) {
                    _startDestination.value = Routes.MAIN_SCREEN
                } else {
                    _startDestination.value = Routes.ONBOARDING_SCREEN
                }
                _isLoading.value = false
            }
        }
    }
}