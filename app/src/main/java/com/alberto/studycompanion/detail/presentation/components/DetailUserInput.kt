package com.alberto.studycompanion.detail.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailUserInput(
    onTimerChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    Row(modifier = modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(Color.White)
        .padding(19.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween)
    {

        Text(
            text = "Set the 'pomodoro' duration: ",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        OutlinedTextField(
            value = "",
            onValueChange = onTimerChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "25") },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colorScheme.primary,
                containerColor = Color.White,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                placeholderColor = MaterialTheme.colorScheme.tertiary.copy(
                    alpha = 0.5f
                ),
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.tertiary.copy(
                    alpha = 0.5f
                )
            )
        )

    }

}

