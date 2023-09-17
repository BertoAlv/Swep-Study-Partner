package com.alberto.studycompanion.authentication.presentation.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alberto.studycompanion.authentication.domain.usecase.LoginUseCases
import com.alberto.studycompanion.authentication.domain.usecase.PasswordResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCases: LoginUseCases) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun onEvent(event: LoginEvent) {

        when (event) {
            is LoginEvent.EmailChanged -> {
                state = state.copy(
                    email = event.email
                )
            }

            LoginEvent.Login -> {
                login()
            }

            is LoginEvent.PasswordChanged -> {
                state = state.copy(
                    password = event.password
                )
            }
        }
    }

    private fun login() {

        state = state.copy(
            emailError = null,
            passwordError = null
        )

        if (!loginUseCases.validateEmailUseCase(state.email)){
            state = state.copy(
                emailError = "This email address is not valid"
            )
        }
        val passwordResult = loginUseCases.validatePasswordUseCase(state.password)
        if (passwordResult is PasswordResult.Invalid){
            state = state.copy(
                passwordError = passwordResult.errorMessage
            )
        }
        if (state.emailError == null && state.passwordError == null){
            state = state.copy(
                isLoading = true
            )
            viewModelScope.launch {
                loginUseCases.loginWithEmailUseCase(state.email,state.password).onSuccess {
                    state = state.copy(
                        isLoggedIn = true
                    )
                    Log.d("Comprobante","LOGIN CON EXITO")
                }.onFailure {
                    state = state.copy(
                        emailError = it.message
                    )
                }
                state = state.copy(
                    isLoading = false
                )
            }
        }
    }

}