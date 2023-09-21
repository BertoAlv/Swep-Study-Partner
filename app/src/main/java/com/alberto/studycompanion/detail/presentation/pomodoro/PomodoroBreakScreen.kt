package com.alberto.studycompanion.detail.presentation.pomodoro

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alberto.studycompanion.core.StudyTimer
import com.alberto.studycompanion.detail.presentation.pomodoro.components.DetailDescription

@Composable
fun PomodoroBreakScreen(
    onBreakFinished: () -> Unit,
    onFinishSession: () -> Unit,
    pomodoroTime: Int?,
    modifier: Modifier = Modifier.fillMaxSize()
) {

    if (pomodoroTime != null) {

        Box(modifier = modifier,
            contentAlignment = Alignment.Center){

            DetailDescription(
                modifier = Modifier.align(Alignment.TopCenter).padding(14.dp),
                description = "This is your time to relax, it's recommended that you get up, do some small talk, or distract your mind from the task in general."
            )

            Column(modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (pomodoroTime <= 10) {
                    StudyTimer(
                        totalTime = 2.times(60).toLong() * 1000L,
                        handleColor = Color.Red,
                        inactiveBarColor = Color.DarkGray,
                        activeBarColor = Color(0xFFAC0707),
                        onPomodoroFinished = { onBreakFinished() },
                        modifier = Modifier.size(200.dp)
                    )
                }else{
                    StudyTimer(
                        totalTime = 5.times(60).toLong() * 1000L,
                        handleColor = Color.Red,
                        inactiveBarColor = Color.DarkGray,
                        activeBarColor = Color(0xFFAC0707),
                        onPomodoroFinished = { onBreakFinished() },
                        modifier = Modifier.size(200.dp)
                    )

                }

                Button(
                    modifier = Modifier.padding(vertical = 20.dp),
                    onClick = { onFinishSession() },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White
                    )
                ) {
                    Text(
                        text = "Finish Session",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }

            }

        }

    }

}
