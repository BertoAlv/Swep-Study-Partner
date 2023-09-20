package com.alberto.studycompanion.detail.presentation.pomodoro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alberto.studycompanion.R
import com.alberto.studycompanion.detail.presentation.pomodoro.components.DetailDescription
import com.alberto.studycompanion.detail.presentation.pomodoro.components.DetailUserInput
import com.alberto.studycompanion.detail.presentation.pomodoro.timer.PomodoroEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PomodoroScreen(
    onBack: () -> Unit,
    onTimerStarted: (Int) -> Unit,
    viewModel: PomodoroViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val state = viewModel.state

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "POMODORO",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }, navigationIcon = {
            IconButton(onClick = { onBack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
            }
        })
    })
    {
        Column(modifier = Modifier
            .padding(it)
            .padding(horizontal = 20.dp),
            horizontalAlignment = CenterHorizontally)
        {
            DetailDescription(description = stringResource(id = R.string.pomodoro_description))

            DetailUserInput(state, onEvent = { viewModel.onEvent(it) }, modifier = Modifier.padding(vertical = 10.dp))
            
            Text(
                text = "Start Timer",
                modifier = Modifier.padding(vertical = 10.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )

            Image(
                painter = painterResource(id = R.drawable.baseline_play_circle_24),
                contentDescription = "Start Timer",
                modifier = Modifier.clickable {
                    onTimerStarted(state.minutes.toInt())
                }
            )

        }
    }


}