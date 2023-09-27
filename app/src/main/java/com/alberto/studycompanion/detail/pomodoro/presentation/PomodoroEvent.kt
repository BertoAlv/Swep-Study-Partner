package com.alberto.studycompanion.detail.pomodoro.presentation

sealed interface PomodoroEvent {

    data class TimerChanged(val time : String) : PomodoroEvent

}