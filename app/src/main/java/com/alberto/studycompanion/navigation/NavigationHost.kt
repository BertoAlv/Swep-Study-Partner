package com.alberto.studycompanion.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.alberto.studycompanion.authentication.presentation.login.LoginScreen
import com.alberto.studycompanion.authentication.presentation.signup.SignUpScreen
import com.alberto.studycompanion.detail.feynman.data.VoiceToTextParser
import com.alberto.studycompanion.detail.feynman.presentation.FeynmanScreen
import com.alberto.studycompanion.detail.pomodoro.presentation.breakscreen.PomodoroBreakScreen
import com.alberto.studycompanion.detail.pomodoro.presentation.PomodoroScreen
import com.alberto.studycompanion.detail.pomodoro.presentation.timer.PomodoroTimerScreen
import com.alberto.studycompanion.detail.todolist.presentation.detail.DetailScreen
import com.alberto.studycompanion.detail.todolist.presentation.todo.ToDoScreen
import com.alberto.studycompanion.home.presentation.HomeScreen
import com.alberto.studycompanion.onboarding.presentation.OnboardingScreen

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination: NavigationRoute,
    voiceToTextParser: VoiceToTextParser
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
                onSettings = { navHostController.navigate(NavigationRoute.Settings.route)},
                onMethodClick = { navHostController.navigate(NavigationRoute.Detail.route + "/${it.name}") }
            )
        }

        composable(NavigationRoute.Detail.route + "/{methodName}",
            arguments = listOf(navArgument("methodName"){
                type = NavType.StringType
                nullable = true
                defaultValue = null
            }
            ))
        {
            when (it.arguments?.getString("methodName")){
                "POMODORO" -> PomodoroScreen(
                    onBack = { navHostController.popBackStack() } ,
                    onTimerStarted = { navHostController.navigate(NavigationRoute.PomodoroTimer.route + "/${it}") }
                )
                "TO DO LIST" -> ToDoScreen(
                    onBack = { navHostController.popBackStack() },
                    onNewTask = { navHostController.navigate(NavigationRoute.ToDoDetail.route) }
                )
                "FEYNMAN" -> FeynmanScreen(
                    voiceToTextParser = voiceToTextParser,
                    onBack = { navHostController.popBackStack() }
                )
            }
        }



        composable(NavigationRoute.PomodoroTimer.route + "/{timeInMinutes}",
            arguments = listOf(navArgument("timeInMinutes"){
                type = NavType.IntType
            }
            ))
        {
            PomodoroTimerScreen(
                timeInMinutes = it.arguments?.getInt("timeInMinutes"),
                onPomodoroFinished = { navHostController.popBackStack()
                    navHostController.navigate(NavigationRoute.PomodoroBreak.route + "/${it}" ) },
                onFinish = { navHostController.popBackStack() }
            )
        }

        composable(NavigationRoute.PomodoroBreak.route + "/{breakTime}",
            arguments = listOf(navArgument("breakTime") {
                type = NavType.IntType
            }
            ))
        {
            PomodoroBreakScreen(
                pomodoroTime = it.arguments?.getInt("breakTime"),
                onBreakFinished = { navHostController.popBackStack()
                    navHostController.navigate(NavigationRoute.PomodoroTimer.route + "/${it.arguments?.getInt("breakTime")}") },
                onFinishSession = { navHostController.popBackStack() }
            )
        }

        composable(NavigationRoute.ToDoDetail.route) {
            DetailScreen(
                onBack = { navHostController.popBackStack() },
                onSave = {
                    navHostController.popBackStack()
                    navHostController.popBackStack()
                    navHostController.navigate(NavigationRoute.Detail.route + "/TO DO LIST")
                }
            )
        }

        composable(NavigationRoute.Settings.route) {
            Text(text = "ESTAMOS EN SETTINGS")
        }



    }
}