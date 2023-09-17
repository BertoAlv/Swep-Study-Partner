package com.alberto.studycompanion.core

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun StudyTitle (
    title : String = "Welcome to monumental habits",
    modifier: Modifier = Modifier
) {
    Text(text = title, modifier = modifier,
    style = MaterialTheme.typography.headlineSmall.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        color = MaterialTheme.colorScheme.tertiary
    ),
    textAlign = TextAlign.Center)
}