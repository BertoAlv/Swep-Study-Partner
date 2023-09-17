package com.alberto.studycompanion.onboarding.presentation

import androidx.annotation.DrawableRes
import com.alberto.studycompanion.R

data class OnboardingPagerInfo(
    @DrawableRes val appName : Int = R.drawable.logo_no_bg,
    val title : String,
    val subtitle : String,
    @DrawableRes val image : Int
)
