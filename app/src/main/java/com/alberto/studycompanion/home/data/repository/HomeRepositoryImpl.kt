package com.alberto.studycompanion.home.data.repository

import androidx.compose.ui.graphics.Color
import com.alberto.studycompanion.R
import com.alberto.studycompanion.home.domain.models.Method
import com.alberto.studycompanion.home.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor() : HomeRepository {

    override fun getAllMethods(): List<Method> {
        return listOf(Method("POMODORO", R.drawable.pomodo_timer , Color(0xFFfedfbe)),
            Method("OTRO CUALQUIERA", R.drawable.logo_no_bg ,Color.White))
    }

}