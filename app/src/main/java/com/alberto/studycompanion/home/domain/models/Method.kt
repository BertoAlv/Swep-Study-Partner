package com.alberto.studycompanion.home.domain.models
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Method (

    val name : String,
    @DrawableRes val image: Int,
    val color : Color

)