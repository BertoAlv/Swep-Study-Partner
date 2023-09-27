package com.alberto.studycompanion.detail.pomodoro.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alberto.studycompanion.detail.pomodoro.presentation.PomodoroState
import com.alberto.studycompanion.detail.pomodoro.presentation.PomodoroEvent


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun DetailUserInput(
    state : PomodoroState,
    onEvent : (PomodoroEvent) -> Unit,
    modifier: Modifier = Modifier,
) {

    var isNumeric by remember { mutableStateOf(true) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(modifier = modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(Color.White)
        .padding(19.dp),
        verticalAlignment = Alignment.CenterVertically)
    {

        Text(
            text = "Set 'pomodoro' duration: ",
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
            isError = !isNumeric,
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
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
        )

        if (!isNumeric) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Enter a valid number", color = MaterialTheme.colorScheme.error)

        }

        Text(
            text = "minutes",
            fontSize = 14.sp,
            modifier = Modifier.padding(vertical = 12.dp)
        )

    }

}

