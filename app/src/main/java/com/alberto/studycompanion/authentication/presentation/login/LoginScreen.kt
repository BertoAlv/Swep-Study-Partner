package com.alberto.studycompanion.authentication.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alberto.studycompanion.R
import com.alberto.studycompanion.authentication.presentation.login.components.LoginForm
import com.alberto.studycompanion.core.StudyTitle

@Composable
fun LoginScreen(
    onSignUp:() -> Unit,
    onLogin:() -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LaunchedEffect(state.isLoggedIn){
        if (state.isLoggedIn){
            onLogin()
        }
    }

    Box(modifier = Modifier.fillMaxSize()){

        Image(painter = painterResource(id = R.drawable.login_background2),
            contentDescription = "login background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1f)
                .graphicsLayer {
                    scaleX = 0.87f
                    scaleY = 0.87f
                }
        )

        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.background
                        )
                    )
                )
        )

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            Spacer(modifier = Modifier)
            Spacer(modifier = Modifier)
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                StudyTitle(title = "WELCOME TO SWEP:")
                StudyTitle(title = "YOUR STUDY PARTNER")
//                Spacer(modifier = Modifier.height(8.dp))
//                Image(
//                    painter = painterResource(id = R.drawable.logo_no_bg),
//                    contentDescription = "Logo",
//                    modifier = Modifier.height(50.dp)
//                )
            }

            LoginForm(state, viewModel::onEvent , onSignUp)
        }
    }

}