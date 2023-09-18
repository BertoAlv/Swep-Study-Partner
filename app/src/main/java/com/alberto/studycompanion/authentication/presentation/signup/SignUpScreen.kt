package com.alberto.studycompanion.authentication.presentation.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.alberto.studycompanion.R
import com.alberto.studycompanion.authentication.presentation.signup.components.SignUpForm

@Composable
fun SignUpScreen(
    onGoLogin: () -> Unit,
    onSignUp: () -> Unit,
    viewModel : SignUpViewModel = hiltViewModel()
) {
    var state = viewModel.state

    LaunchedEffect(state.signIn){
        if (state.signIn){
            onGoLogin()
        }
    }

    LaunchedEffect(state.isSignedUp){
        if (state.isSignedUp){
            onSignUp()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {

        Image(
            painter = painterResource(id = R.drawable.login_background),
            contentDescription = "Sign up form",
            Modifier.aspectRatio(0.92f))

        SignUpForm(state, viewModel::onEvent, Modifier.fillMaxWidth())
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

        if (state.isLoading){
            CircularProgressIndicator()
        }

    }
}

