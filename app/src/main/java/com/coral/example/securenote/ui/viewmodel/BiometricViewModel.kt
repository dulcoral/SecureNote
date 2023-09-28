package com.coral.example.securenote.ui.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coral.example.securenote.utils.showBiometricPrompt
import kotlinx.coroutines.launch

class BiometricViewModel : ViewModel() {
    private val _authenticationState =
        MutableLiveData<AuthenticationState>(AuthenticationState.Idle)
    val authenticationState: LiveData<AuthenticationState> get() = _authenticationState

    @RequiresApi(Build.VERSION_CODES.R)
    fun authenticate(context: Context) {
        viewModelScope.launch {
            showBiometricPrompt(
                onAuthenticateSuccess = {
                    _authenticationState.value = AuthenticationState.Success
                },
                onAuthenticateError = {
                    _authenticationState.value = AuthenticationState.Failure
                },
                context = context
            )
        }
    }

    fun resetState() {
        _authenticationState.value = AuthenticationState.Idle
    }

    sealed class AuthenticationState {
        object Idle : AuthenticationState()
        object Success : AuthenticationState()
        object Failure : AuthenticationState()
    }
}
