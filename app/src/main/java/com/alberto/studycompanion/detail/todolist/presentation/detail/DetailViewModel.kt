package com.alberto.studycompanion.detail.todolist.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alberto.studycompanion.detail.todolist.domain.models.Task
import com.alberto.studycompanion.detail.todolist.domain.usecase.AddTaskUseCase
import com.alberto.studycompanion.detail.todolist.domain.usecase.GetTaskByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase
) : ViewModel() {

    var state by mutableStateOf(DetailState())
        private set

    fun onEvent(event: DetailEvent){

        when(event) {

            DetailEvent.TaskSave -> {
                viewModelScope.launch {
                    val task = Task(
                        id = state.id ?: UUID.randomUUID().toString(),
                        name = state.taskName,
                        isDone = false
                    )
                    addTaskUseCase(task)
                }

                state = state.copy(
                    isSaved = true
                )
            }

            is DetailEvent.NameChange -> state = state.copy(
                taskName = event.name
            )

        }
    }

}