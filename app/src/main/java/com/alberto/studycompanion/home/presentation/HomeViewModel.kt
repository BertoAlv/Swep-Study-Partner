package com.alberto.studycompanion.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.alberto.studycompanion.home.domain.usecases.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeUseCases: HomeUseCases) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        getMethods()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
        }
    }

    private fun getMethods(){
        state = state.copy(
            methods = homeUseCases.getMethodsUseCase()
        )
    }

}