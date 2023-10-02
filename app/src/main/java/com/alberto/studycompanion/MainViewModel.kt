package com.alberto.studycompanion

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alberto.studycompanion.authentication.domain.usecase.GetUserIdUseCase
import com.alberto.studycompanion.authentication.domain.usecase.LogOutUseCase
import com.alberto.studycompanion.onboarding.domain.usecase.HasSeenOnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val hasSeenOnboardingUseCase: HasSeenOnboardingUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
    private val logOutUseCase: LogOutUseCase
) : ViewModel() {

    var hasSeenOnboarding by mutableStateOf(hasSeenOnboardingUseCase())
        private set

    var isLoggedIn by mutableStateOf(getUserIdUseCase() != null)
        private set

    fun logout(){
        viewModelScope.launch {
            logOutUseCase()
        }
    }
}