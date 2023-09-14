package com.alberto.studycompanion.core

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun StudyTitle (
    title : String = "Welcome to monumental habits",
    modifier: Modifier = Modifier
) {
    Text(text = title.uppercase(), modifier = modifier,
    style = MaterialTheme.typography.headlineSmall.copy(
    fontWeight = FontWeight.Bold,
    color = MaterialTheme.colorScheme.tertiary
    ),
    textAlign = TextAlign.Center)
}