package com.alberto.studycompanion.detail.todolist.presentation.todo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alberto.studycompanion.core.StudyCheckbox
import com.alberto.studycompanion.detail.todolist.domain.models.Task

@Composable
fun ListItem(
    task : Task,
    onCheckedChange:() -> Unit,
    onDelete:() -> Unit,
    modifier: Modifier = Modifier
) {
    var isLongPressActive by remember { mutableStateOf(false) }

    Row(modifier = modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(Color.White)
        .padding(19.dp)
        .pointerInput(Unit) {
            detectTapGestures(
                onLongPress = {
                    isLongPressActive = true
                }
            )
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween)
    {
        Text(text = task.name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        StudyCheckbox(isChecked = task.isDone, onCheckedChange = { onCheckedChange() })
    }

    if (isLongPressActive) {
        DeletePopup(
            onDeleteClick = {
                onDelete()
                isLongPressActive = false
            },
            onCancelClick = {
                isLongPressActive = false
            }
        )
    }
}

@Composable
fun DeletePopup(onDeleteClick: () -> Unit, onCancelClick: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onCancelClick() },
        title = {
            Text(text = "Delete Task")
        },
        text = {
            Text(text = "Are you sure you want to delete this task?")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDeleteClick()
                }
            ) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onCancelClick()
                }
            ) {
                Text("Cancel")
            }
        }
    )
}

