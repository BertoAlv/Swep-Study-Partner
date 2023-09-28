package com.alberto.studycompanion.detail.pomodoro.presentation

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alberto.studycompanion.R
import com.alberto.studycompanion.detail.pomodoro.presentation.components.DetailDescription
import com.alberto.studycompanion.detail.pomodoro.presentation.components.DetailUserInput

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PomodoroScreen(
    onBack: () -> Unit,
    onTimerStarted: (Int) -> Unit,
    viewModel: PomodoroViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val state = viewModel.state

    var canVibrate by remember {
        mutableStateOf(false)
    }

    val vibrateEffectLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            canVibrate = isGranted
        }
    )

    LaunchedEffect(key1 = vibrateEffectLauncher) {
        vibrateEffectLauncher.launch(Manifest.permission.VIBRATE)
    }

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "POMODORO",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
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