package com.alberto.studycompanion.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alberto.studycompanion.R
import com.alberto.studycompanion.home.domain.models.Method
import com.alberto.studycompanion.home.presentation.components.HomeMethod
import com.alberto.studycompanion.home.presentation.components.HomeQuote
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNewMethod: () -> Unit,
    onSettings: () -> Unit,
    onMethodClick: (Method) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val quotes = listOf(
        "Education is not preparation for life; education is life itself.",
        "The mind is not a vessel to be filled, but a fire to be kindled.",
        "An investment in knowledge pays the best interest."
    )

    val authors = listOf(
        "John Dewey",
        "Plutarch",
        "Benjamin Franklin"
    )

    val random = Random.nextInt(3)

    val state = viewModel.state

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "HOME",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }, navigationIcon = {
            IconButton(onClick = { onSettings() }) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "settings")
            }
        })
    },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNewMethod() },
                containerColor = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Create New Method",
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 20.dp)
        ) {

            item {
                HomeQuote(
                    quote = quotes[random],
                    author = authors[random],
                    imageId = R.drawable.login_background3,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(state.methods) {
                HomeMethod(
                    method = it,
                    onMethodClick = { onMethodClick(it) },
                    modifier = Modifier.padding(vertical = 6.dp)
                )
            }
        }
    }
}