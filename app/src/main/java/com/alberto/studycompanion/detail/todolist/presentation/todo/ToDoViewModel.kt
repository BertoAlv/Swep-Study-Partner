package com.alberto.studycompanion.detail.todolist.presentation.todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alberto.studycompanion.detail.todolist.domain.usecase.CompleteTaskUseCase
import com.alberto.studycompanion.detail.todolist.domain.usecase.DeleteTaskUseCase
import com.alberto.studycompanion.detail.todolist.domain.usecase.GetTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val completeTaskUseCase: CompleteTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val getTasksUseCase: GetTasksUseCase
    ) : ViewModel() {

    var state by mutableStateOf(ToDoState())
        private set

    init {
        getTasks()
    }

    fun onEvent(event: ToDoEvent) {

        when (event) {

            is ToDoEvent.completeTask ->
                viewModelScope.launch {
                    completeTaskUseCase(event.task)
                    getTasks()
                }

            is ToDoEvent.deleteTask -> viewModelScope.launch {
                deleteTaskUseCase(event.id)
                getTasks()
            }
        }

    }

    private fun getTasks(){
        viewModelScope.launch {
            getTasksUseCase().collectLatest {
                state = state.copy(
                    tasks = it
                )
            }
        }
    }

}