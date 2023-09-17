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

    val pages = listOf(
        OnboardingPagerInfo(title = "Set Your Objectives", subtitle = "You set your objectives, we help you to achieve them", image = R.drawable.sample_image1),
        OnboardingPagerInfo(title = "Avoid Distractions", subtitle = "StudyPal helps you to stay focused so you can success on your journey.", image = R.drawable.distractions_image),
        OnboardingPagerInfo(title = "Watch your progress", subtitle = "Sign Up now and see how much you can improve.", image = R.drawable.progress_img),
    )


    OnboardingPager(pages, onFinish = { viewModel.completeOnboarding() })
}