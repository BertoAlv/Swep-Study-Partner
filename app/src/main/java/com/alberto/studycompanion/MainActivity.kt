package com.alberto.studycompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.alberto.studycompanion.detail.feynman.data.VoiceToTextParser
import com.alberto.studycompanion.navigation.NavigationHost
import com.alberto.studycompanion.navigation.NavigationRoute
import com.alberto.studycompanion.ui.theme.StudyCompanionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val voiceToTextParser by lazy {
        VoiceToTextParser(application)
    }

    private val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyCompanionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationHost(
                        navHostController = navController,
                        startDestination = getStartDestination(),
                        voiceToTextParser,
                        logout = { viewModel.logout() })
                }
            }
        }
    }

    private fun getStartDestination() : NavigationRoute {
        if (viewModel.isLoggedIn){
            return NavigationRoute.Home
        }
        return if (viewModel.hasSeenOnboarding){
            NavigationRoute.Login
        } else NavigationRoute.Onboarding
    }

}
