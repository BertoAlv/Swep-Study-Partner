package com.alberto.studycompanion.detail.pomodoro.presentation.timer

import android.content.Context

sealed interface PomodoroTimerEvent{

    data class TimerEnded(val context : Context) : PomodoroTimerEvent

    data class BreakEnded(val context : Context) : PomodoroTimerEvent

}