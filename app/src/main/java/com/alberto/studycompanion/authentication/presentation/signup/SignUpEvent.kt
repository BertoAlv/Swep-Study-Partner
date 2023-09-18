package com.alberto.studycompanion.authentication.presentation.signup

sealed interface SignUpEvent {
    data class EmailChanged(val email : String) : SignUpEvent
    data class PasswordChanged(val password : String) : SignUpEvent
    object SignIn : SignUpEvent
    object SignUp : SignUpEvent
}