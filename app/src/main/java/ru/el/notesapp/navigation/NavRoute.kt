package ru.el.notesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.el.notesapp.MainViewModel
import ru.el.notesapp.screens.AddScreen
import ru.el.notesapp.screens.MainScreen
import ru.el.notesapp.screens.NoteScreen
import ru.el.notesapp.screens.StartScreen

sealed class NavRoute(val route: String) {
    object Start: NavRoute("start_screen")
    object Main: NavRoute("main_screen")
    object Add: NavRoute("add_screen")
    object Note: NavRoute("note_screen")
}

@Composable
fun NotesNavHost(mViewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController=navController, startDestination=NavRoute.Start.route){
        composable(NavRoute.Start.route) {StartScreen(navController=navController, viewModel = mViewModel)}
        composable(NavRoute.Start.route) {AddScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Start.route) {MainScreen(navController = navController, viewModel = mViewModel) }
        composable(NavRoute.Start.route) {NoteScreen(navController = navController, viewModel = mViewModel) }
    }
}