package com.coral.example.securenote.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.coral.example.securenote.R
import com.coral.example.securenote.ui.components.AddNoteButton
import com.coral.example.securenote.ui.viewmodel.SecureNotesViewModel

enum class SecureNoteScreen(@StringRes val title: Int) {
    Home(title = R.string.home_screen_name),
    AddNote(title = R.string.add_note_screen_name),
    EditNote(title = R.string.edit_note_screen_name),
}

@Composable
fun AppBar(
    currentScreen: SecureNoteScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun SecureNotesScreen(
    viewModel: SecureNotesViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = SecureNoteScreen.valueOf(
        backStackEntry?.destination?.route ?: SecureNoteScreen.Home.name
    )
    val list = viewModel.listNotes.collectAsState(initial = emptyList()).value


    Scaffold(
        topBar = {
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
        }

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SecureNoteScreen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
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

