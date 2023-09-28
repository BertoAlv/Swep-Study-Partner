package com.alberto.studycompanion.detail.pomodoro.presentation.timer


import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alberto.studycompanion.core.StudyTimer
import com.alberto.studycompanion.detail.pomodoro.presentation.components.DetailDescription

@Composable
fun PomodoroTimerScreen(
    timeInMinutes : Int?,
    onPomodoroFinished:(Int) -> Unit,
    viewModel: PomodoroTimerViewModel = hiltViewModel(),
    onFinish:() -> Unit,
    modifier: Modifier = Modifier.fillMaxSize()
) {

    val time = timeInMinutes?.times(60)

    val context = LocalContext.current

    Box(modifier = modifier,
        contentAlignment = Alignment.Center){

        DetailDescription(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(14.dp),
            description = "Now focus on the task that you need to do. If you have an emergency you can pause the timer."
        )

        Column(modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (time != null) {
                StudyTimer(
                    totalTime = time.toLong() * 1000L,
                    handleColor = Color.Red,
                    inactiveBarColor = Color.DarkGray,
                    activeBarColor = Color(0xFFAC0707),
                    onPomodoroFinished = {
                        viewModel.onEvent(PomodoroTimerEvent.TimerEnded(context))
                        onPomodoroFinished(timeInMinutes)
                                         },
                    modifier = Modifier.size(200.dp)
                )
            }

            Button(
                modifier = Modifier.padding(vertical = 20.dp),
                onClick = { onFinish() },
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