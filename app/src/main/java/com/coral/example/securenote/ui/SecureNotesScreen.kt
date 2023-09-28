package com.coral.example.securenote.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.FabPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.coral.example.securenote.R
import com.coral.example.securenote.ui.components.AddNoteButton
import com.coral.example.securenote.ui.components.AppBar
import com.coral.example.securenote.ui.viewmodel.SecureNotesViewModel

enum class SecureNoteScreen(@StringRes val title: Int) {
    Home(title = R.string.home_screen_name),
    AddNote(title = R.string.add_note_screen_name),
    EditNote(title = R.string.edit_note_screen_name),
    AuthNote(title = R.string.auth_note_screen_name),
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun SecureNotesScreen(
    viewModel: SecureNotesViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = SecureNoteScreen.valueOf(
        backStackEntry?.destination?.route ?: SecureNoteScreen.AuthNote.name
    )
    val list = viewModel.listNotes.collectAsState(initial = emptyList()).value

    DisposableEffect(navController) {
        val callback =
            NavController.OnDestinationChangedListener { controller, destination, arguments ->

                if (destination.route != SecureNoteScreen.EditNote.name) {
                    viewModel.clearNoteFields()
                }
            }

        navController.addOnDestinationChangedListener(callback)

        onDispose {
            navController.removeOnDestinationChangedListener(callback)
        }
    }

    Scaffold(
        topBar = {
            if (currentScreen != SecureNoteScreen.AuthNote)
                AppBar(
                    currentScreen = currentScreen,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() }
                )
        },
        floatingActionButton = {
            if (currentScreen == SecureNoteScreen.Home) {
                AddNoteButton(onButtonClick = {
                    navController.navigate(SecureNoteScreen.AddNote.name)
                })
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SecureNoteScreen.AuthNote.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = SecureNoteScreen.AuthNote.name) {
                AuthScreen(
                    onSuccessAuth = {
                        navController.navigate(SecureNoteScreen.Home.name) {
                            popUpTo(SecureNoteScreen.AuthNote.name) {
                                inclusive = true
                            }
                        }

                    },
                )
            }
            composable(route = SecureNoteScreen.Home.name) {
                HomeScreen(
                    notes = list,
                    viewModel = viewModel,
                    onEdit = {
                        navController.navigate(SecureNoteScreen.EditNote.name)
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = SecureNoteScreen.AddNote.name) {
                AddNoteScreen(
                    viewModel = viewModel,
                    onSaveClick = {
                        viewModel.saveNote(isEdit = false)
                        navController.popBackStack(
                            SecureNoteScreen.Home.name,
                            inclusive = false
                        )
                    },
                    modifier = Modifier.fillMaxHeight()
                )
            }
            composable(route = SecureNoteScreen.EditNote.name) {
                AddNoteScreen(
                    viewModel = viewModel,
                    onSaveClick = {
                        viewModel.saveNote(isEdit = true)
                        navController.popBackStack(
                            SecureNoteScreen.Home.name,
                            inclusive = false
                        )
                    },
                    modifier = Modifier.fillMaxHeight()
                )
            }
        }
    }
}

