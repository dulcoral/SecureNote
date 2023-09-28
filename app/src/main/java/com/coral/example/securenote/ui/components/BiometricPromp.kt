package com.coral.example.securenote.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import com.coral.example.securenote.ui.viewmodel.BiometricViewModel

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun BiometricPrompt(
    onSuccessAuth: () -> Unit,
    promptShown: MutableState<Boolean>
) {
    val context = LocalContext.current
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
                viewModel.resetState()
            }

            is BiometricViewModel.AuthenticationState.Idle -> viewModel.authenticate(context)
        }
    }

}
