package com.alberto.studycompanion.detail.feynman.presentation

import android.Manifest
import android.app.Application
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.material.icons.rounded.Stop
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alberto.studycompanion.MainActivity
import com.alberto.studycompanion.R
import com.alberto.studycompanion.StudyCompanionApp
import com.alberto.studycompanion.detail.feynman.data.VoiceToTextParser
import com.alberto.studycompanion.detail.pomodoro.presentation.components.DetailDescription

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun FeynmanScreen(
    voiceToTextParser: VoiceToTextParser,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {

    val state by voiceToTextParser.state.collectAsState()

    var canRecord by remember {
        mutableStateOf(false)
    }

    val recordAudioLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            canRecord = isGranted
        }
    )

    LaunchedEffect(key1 = recordAudioLauncher) {
        recordAudioLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "FEYNMAN TECHNIQUE",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }, navigationIcon = {
            IconButton(onClick = { onBack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
            }
        })
    },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = Color.White,
            onClick = {
                if (state.isSpeaking){
                    voiceToTextParser.stopContinuousListening()
                }else{
                    voiceToTextParser.startContinuousListening()
                }
            }
        ) {
            AnimatedContent(targetState = state.isSpeaking) { isSpeaking ->
                if (isSpeaking) {
                    Icon(
                        imageVector = Icons.Rounded.Stop,
                        contentDescription = "stop recording",
                        tint = MaterialTheme.colorScheme.primary
                    )
                } else {
                    Icon(
                        imageVector = Icons.Rounded.Mic,
                        contentDescription = "start recording",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            
        }
    })
    {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            DetailDescription(description = stringResource(id = R.string.feynman_description))

            AnimatedContent(modifier = Modifier.padding(vertical = 20.dp), targetState = state.isSpeaking) {isSpeaking ->
                if (isSpeaking){
                    Text(text = "Speaking...")
                }else{
                    Text(text = state.spokenText.ifEmpty { "Click on the mic to start recording." })
                }
            }

        }
    }

}