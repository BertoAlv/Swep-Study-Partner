package com.alberto.studycompanion.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alberto.studycompanion.authentication.presentation.login.LoginScreen
import com.alberto.studycompanion.authentication.presentation.signup.SignUpScreen
import com.alberto.studycompanion.home.presentation.HomeScreen
import com.alberto.studycompanion.onboarding.presentation.OnboardingScreen

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination: NavigationRoute
){
    NavHost(navController = navHostController, startDestination = startDestination.route) {
        composable(NavigationRoute.Onboarding.route) {
            OnboardingScreen {
                navHostController.popBackStack()
                navHostController.navigate(NavigationRoute.Login.route)
            }
        }
        
        composable(NavigationRoute.Login.route){
            LoginScreen(
                onSignUp = { navHostController.navigate(NavigationRoute.SignUp.route) },
                onLogin = { navHostController.popBackStack()
                            navHostController.navigate(NavigationRoute.Home.route)
                }
            )
        }
        
        composable(NavigationRoute.SignUp.route){
            SignUpScreen(
                onGoLogin = { navHostController.popBackStack() },
                onSignUp = { navHostController.navigate(NavigationRoute.Home.route) {
                    popUpTo(navHostController.graph.id){
                        inclusive = true
                    }
                }
                }
            )
        }

        composable(NavigationRoute.Home.route){
            HomeScreen(
                onNewMethod = { },
                onSettings = { navHostController.navigate(NavigationRoute.Settings.route) }
            )
        }

        composable(NavigationRoute.Settings.route) {
            Text(text = "ESTAMOS EN SETTINGS")
        }
    }
}