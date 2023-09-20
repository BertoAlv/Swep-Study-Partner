package com.alberto.studycompanion.detail.presentation.pomodoro.timer

sealed interface PomodoroEvent {

    data class TimerChanged(val time : String) : PomodoroEvent

}