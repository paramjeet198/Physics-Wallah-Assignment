package com.example.physicswallahassignment.ui.navigation

import CharacterDetailScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.physicswallahassignment.ui.screens.characterlist.CharacterListScreen

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.CHARACTER_LIST,
    ) {

        composable(NavigationRoutes.CHARACTER_LIST) {
            CharacterListScreen(navController)
        }

        composable(
            NavigationRoutes.DETAILS + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            }),
        ) {
            val id = it.arguments!!.getInt("id")
            CharacterDetailScreen(navController)
        }
    }

}