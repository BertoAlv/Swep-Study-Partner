package com.alberto.studycompanion.detail.todolist.presentation.todo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alberto.studycompanion.detail.todolist.presentation.todo.components.ListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoScreen(
    onBack:() -> Unit,
    onNewTask:() -> Unit,
    viewModel: ToDoViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val state = viewModel.state

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "TO DO LIST",
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
                onClick = { onNewTask() },
                containerColor = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "New Task",
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
            items(state.tasks){
                ListItem(
                    task = it,
                    onCheckedChange = { viewModel.onEvent(ToDoEvent.completeTask(it)) },
                    onDelete = { viewModel.onEvent(ToDoEvent.deleteTask(it.id)) },
                    modifier = Modifier.padding(vertical = 6.dp)
                )
            }
        }
    }

}