package com.alberto.studycompanion.authentication.presentation.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alberto.studycompanion.authentication.domain.usecase.PasswordResult
import com.alberto.studycompanion.authentication.domain.usecase.SignUpUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(val signUpUseCases: SignUpUseCases): ViewModel() {

    var state by mutableStateOf(SignUpState())
        private set

    fun onEvent( event: SignUpEvent ) {

        when(event){

            is SignUpEvent.EmailChanged -> state = state.copy(email = event.email)

            is SignUpEvent.PasswordChanged -> state = state.copy(password = event.password)

            SignUpEvent.SignIn -> state = state.copy(signIn = true)

            SignUpEvent.SignUp -> signUp()
        }
    }

    private fun signUp() {

        state = state.copy(
            emailError = null,
            passwordError = null
        )

        if (!signUpUseCases.validateEmailUseCase(state.email)){
            state = state.copy(
                emailError = "This email address is not valid"
            )
        }
        val passwordResult = signUpUseCases.validatePasswordUseCase(state.password)
        if (passwordResult is PasswordResult.Invalid){
            state = state.copy(
                passwordError = passwordResult.errorMessage
            )
        }
        if (state.emailError == null && state.passwordError == null) {
            state = state.copy(
                isLoading = true
            )
            viewModelScope.launch {
                signUpUseCases.signUpWithEmailUseCase(state.email,state.password).onSuccess {
                    state = state.copy(
                        isSignedUp = true
                    )
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