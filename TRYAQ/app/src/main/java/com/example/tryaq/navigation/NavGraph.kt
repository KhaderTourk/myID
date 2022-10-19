package com.example.tryaq.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.example.tryaq.presentation.common.LoadingAddAppointment
import com.example.tryaq.presentation.common.LoadingLogin
import com.example.tryaq.presentation.common.LoadingSignUp
import com.example.tryaq.presentation.common.LoadingViews
import com.example.tryaq.presentation.screens.bottom_nav_screens.BottomNavigationWithSwipeScreen
import com.example.tryaq.presentation.screens.bottom_nav_screens.departments.DepartmentsScreen
import com.example.tryaq.presentation.screens.bottom_nav_screens.home.HomeScreen
import com.example.tryaq.presentation.screens.bottom_nav_screens.results.ResultsScreen
import com.example.tryaq.presentation.screens.bottom_nav_screens.results.result_detail.ResultDetailScreen
import com.example.tryaq.presentation.screens.drawer_screens.*
import com.example.tryaq.presentation.screens.drawer_screens.medicine.MedicinesScreen
import com.example.tryaq.presentation.screens.drawer_screens.medicine.search.SearchScreen
import com.example.tryaq.presentation.screens.drawer_screens.my_account.MyAccountScreen
import com.example.tryaq.presentation.screens.logging.LoginScreen
import com.example.tryaq.presentation.screens.logging.SignUpScreen
import com.example.tryaq.presentation.screens.new_appointment.NewAppointmentScreen
import com.example.tryaq.presentation.screens.notifications.NotificationsScreen
import com.example.tryaq.presentation.screens.services.ServicesScreen
import com.example.tryaq.presentation.screens.splash.SplashScreen
import com.example.tryaq.presentation.screens.welcome.WelcomeScreen
import com.example.tryaq.util.Constants.CANCEL_APPOINTMENT_KEY
import com.example.tryaq.util.Constants.DETAILS_ARGUMENT_KEY
import com.example.tryaq.util.Constants.DETAILS_DEPARTMENT_KEY
import com.example.tryaq.util.Constants.DETAILS_DIAGNOSIS_KEY
import com.example.tryaq.util.Constants.NEW_APPOINTMENT_KEY
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalCoilApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.BottomNavigationWithSwipeScreen.route) {
            BottomNavigationWithSwipeScreen(navController = navController)
        }
        composable(route = Screen.Departments.route) {
            DepartmentsScreen(navController = navController)
        }
        composable(route = Screen.Results.route) {
            ResultsScreen(navController = navController)
        }
        composable(route = Screen.Notifications.route) {
            NotificationsScreen(navController = navController)
        }
        composable(route = Screen.MyAccount.route) {
            MyAccountScreen(navController = navController)
        }
        composable(route = Screen.ContactUs.route) {
            ContactUsScreen(navController = navController)
        }
        composable(route = Screen.History.route) {
            HistoryScreen(navController = navController)
        }
        composable(route = Screen.Importance.route) {
            ImportanceScreen(navController = navController)
        }
        composable(route = Screen.LookingFor.route) {
            LookingForScreen(navController = navController)
        }
        composable(route = Screen.Medicines.route) {
            MedicinesScreen(navController = navController)
        }
        composable(route = Screen.MyAppointments.route) {
            MyAppointmentsScreen(navController = navController)
        }
        composable(route = Screen.LoadingAddAppointment.route) {
            LoadingAddAppointment(navController = navController)
        }
        composable(route = Screen.LoadingSignUp.route) {
            LoadingSignUp(navController = navController)
        }
        composable(route = Screen.LoadingLogin.route) {
            LoadingLogin(navController = navController)
        }
        composable(route = Screen.Login.route ) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.SignUp.route ) {
            SignUpScreen(navController = navController)
        }
        composable(route = Screen.ResultDetail.route ,
            arguments = listOf(navArgument(DETAILS_DIAGNOSIS_KEY) {
                type = NavType.IntType
            })
        ) {
            ResultDetailScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) {

        }
        composable(route = Screen.Services.route,
            arguments = listOf(navArgument(DETAILS_DEPARTMENT_KEY) {
                type = NavType.IntType
            })
        ) {
            ServicesScreen(navController = navController)
        }
        composable(route = Screen.NewAppointment.route,
            arguments = listOf(navArgument(NEW_APPOINTMENT_KEY) {
                type = NavType.IntType
            })
        ) {
            NewAppointmentScreen(navController = navController)
        }
        composable(route = Screen.Loading.route,
            arguments = listOf(navArgument(CANCEL_APPOINTMENT_KEY) {
                type = NavType.IntType
            })
        ) {
            LoadingViews(navController = navController)
        }

        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }
    }
}
