package com.coral.example.securenote.ui.viewmodel

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coral.example.securenote.utils.BiometricAuthenticator

class BiometricViewModel : ViewModel() {
    private val _authenticationState = MutableLiveData<AuthenticationState>()
    val authenticationState: LiveData<AuthenticationState> get() = _authenticationState

    fun authenticate(context: Context) {
        val biometricManager = BiometricManager.from(context)
        if (biometricManager.canAuthenticate(BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS) {
            val authenticator = BiometricAuthenticator(context as FragmentActivity)
            authenticator.authenticate(
                onSuccess = {
                    _authenticationState.value = AuthenticationState.Success
                },
                onFailure = {
                    _authenticationState.value = AuthenticationState.Failure
                },
                onError = { errorCode, errString ->
                    _authenticationState.value =
                        AuthenticationState.Error(errorCode, errString.toString())
                }
            )
        } else {
            _authenticationState.value = AuthenticationState.NotAvailable
        }
    }

    sealed class AuthenticationState {
        object Success : AuthenticationState()
        object Failure : AuthenticationState()
        data class Error(val errorCode: Int, val errorMessage: String) : AuthenticationState()
        object NotAvailable : AuthenticationState()
    }
}
