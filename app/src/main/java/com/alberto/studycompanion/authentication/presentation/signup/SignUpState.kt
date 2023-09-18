package com.alberto.studycompanion.authentication.presentation.signup

data class SignUpState(
    val email : String = "",
    val password : String = "",
    val emailError : String? = null,
    val passwordError : String? = null,
    val isSignedUp : Boolean = false,
    val isLoading : Boolean = false,
    val signIn : Boolean = false
)
