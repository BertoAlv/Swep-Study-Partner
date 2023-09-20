package com.alberto.studycompanion.detail.presentation.pomodoro.timer


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alberto.studycompanion.core.StudyTimer

@Composable
fun PomodoroTimerScreen(
    timeInMinutes : Int?,
    modifier: Modifier = Modifier.fillMaxSize()
) {

    val time = timeInMinutes?.times(60)

    Box(modifier = modifier, contentAlignment = Alignment.Center){

        if (time != null) {
            StudyTimer(
                totalTime = time.toLong() * 1000L,
                handleColor = Color.Red,
                inactiveBarColor = Color.DarkGray,
                activeBarColor = Color(0xFFAC0707),
                modifier = Modifier.size(200.dp)
            )
        }



    }

}