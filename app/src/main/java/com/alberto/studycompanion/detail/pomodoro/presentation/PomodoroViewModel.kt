package com.alberto.studycompanion.detail.pomodoro.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PomodoroViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(PomodoroState())
        private set

    fun onEvent(event: PomodoroEvent) {

        when(event){

            is PomodoroEvent.TimerChanged -> state = state.copy(minutes = event.time)

        }

    }

}