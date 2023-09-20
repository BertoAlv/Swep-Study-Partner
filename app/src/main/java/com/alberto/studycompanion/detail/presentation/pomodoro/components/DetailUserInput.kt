package com.alberto.studycompanion.detail.presentation.pomodoro.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alberto.studycompanion.detail.presentation.pomodoro.PomodoroState
import com.alberto.studycompanion.detail.presentation.pomodoro.timer.PomodoroEvent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailUserInput(
    state : PomodoroState,
    onEvent : (PomodoroEvent) -> Unit,
    modifier: Modifier = Modifier,
) {

    Row(modifier = modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(Color.White)
        .padding(19.dp),
        verticalAlignment = Alignment.CenterVertically)
    {

        Text(
            text = "Set the 'pomodoro' duration: ",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )

        OutlinedTextField(
            value = state.minutes,
            onValueChange = { onEvent(PomodoroEvent.TimerChanged(it)) },
            modifier = Modifier
                .width(60.dp)
                .padding(horizontal = 6.dp),
            textStyle = TextStyle(fontSize = 14.sp, textAlign = TextAlign.Center),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colorScheme.primary,
                containerColor = Color.White,
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                placeholderColor = MaterialTheme.colorScheme.tertiary.copy(
                    alpha = 0.5f
                ),
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.tertiary.copy(
                    alpha = 0.5f
                )
            )
        )

        Text(
            text = "minutes",
            fontSize = 14.sp,
            modifier = Modifier.padding(vertical = 12.dp)
        )

    }

}

