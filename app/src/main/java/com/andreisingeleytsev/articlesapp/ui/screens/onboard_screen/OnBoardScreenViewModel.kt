package com.andreisingeleytsev.articlesapp.ui.screens.onboard_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreisingeleytsev.articlesapp.data.datastore.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardScreenViewModel @Inject constructor(
    private val repository: DataStoreRepository
): ViewModel() {
    fun saveOnBoardingState() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveOnBoardingState(completed = true)
        }
    }
}