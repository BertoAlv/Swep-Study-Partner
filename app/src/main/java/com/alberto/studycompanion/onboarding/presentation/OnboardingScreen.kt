package com.alberto.studycompanion.onboarding.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.alberto.studycompanion.R
import com.alberto.studycompanion.onboarding.presentation.components.OnboardingPager

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    onFinish:() -> Unit
) {

    LaunchedEffect(viewModel.hasSeenOnboarding){
        if (viewModel.hasSeenOnboarding){
            onFinish()
        }
    }

    val pages = listOf<OnboardingPagerInfo>(
        OnboardingPagerInfo(subtitle = "Using bla bla bla...", image = R.drawable.studying_photo),
        OnboardingPagerInfo(subtitle = "Using bla bla bla...", image = R.drawable.sample_image1),
        OnboardingPagerInfo(subtitle = "Esta es la ultima pantalla", image = R.drawable.sample_image2),
    )


    OnboardingPager(pages, onFinish = { viewModel.completeOnboarding() })
}