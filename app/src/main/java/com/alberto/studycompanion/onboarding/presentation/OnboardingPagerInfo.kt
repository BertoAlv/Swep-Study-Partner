package com.alberto.studycompanion.onboarding.presentation

import androidx.annotation.DrawableRes

data class OnboardingPagerInfo(
    val title : String = "appname",
    val subtitle : String,
    @DrawableRes val image : Int
)
