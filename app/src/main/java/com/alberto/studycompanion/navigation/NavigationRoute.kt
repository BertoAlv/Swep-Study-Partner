package com.alberto.studycompanion.navigation

sealed class NavigationRoute(val route : String) {

    object Onboarding : NavigationRoute("onboarding")
    object Login : NavigationRoute("login")
    object SignUp : NavigationRoute("signup")
    object Home : NavigationRoute("home")
    object Settings : NavigationRoute("settings")
    object Detail : NavigationRoute("detail")
    object PomodoroTimer : NavigationRoute("PomodoroTimer")
    object PomodoroBreak : NavigationRoute("PomodoroBreak")

}