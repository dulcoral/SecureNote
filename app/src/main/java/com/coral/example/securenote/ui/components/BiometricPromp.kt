package com.coral.example.securenote.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import com.coral.example.securenote.ui.viewmodel.BiometricViewModel

@Composable
fun BiometricPrompt(
    onSuccessAuth: () -> Unit,
    promptShown: MutableState<Boolean>
) {
    val context = LocalContext.current as FragmentActivity
    val viewModel = BiometricViewModel()
    val authenticationState by viewModel.authenticationState.observeAsState()

    if (!promptShown.value) {
        when (authenticationState) {
            is BiometricViewModel.AuthenticationState.Success -> {
                onSuccessAuth()
                promptShown.value = true
            }

            is BiometricViewModel.AuthenticationState.Failure -> {
                // Handle failure
                promptShown.value = true
            }

            is BiometricViewModel.AuthenticationState.Error -> {
                val error = authenticationState as BiometricViewModel.AuthenticationState.Error
                // Handle error using error.errorCode and error.errorMessage
                promptShown.value = true
            }

            is BiometricViewModel.AuthenticationState.NotAvailable -> {
                // Handle not available state
                promptShown.value = true
            }

            null -> viewModel.authenticate(context)
        }
    }
}


