package com.coral.example.securenote.utils

import android.Manifest
import android.app.KeyguardManager
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.CancellationSignal
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.coral.example.securenote.R

@RequiresApi(Build.VERSION_CODES.R)
fun showBiometricPrompt(
    context: Context,
    onAuthenticateSuccess: () -> Unit,
    onAuthenticateError: () -> Unit
) {
    if (isBiometricSupported(context)) {
        val biometricPrompt = createBiometricPrompt(context, onAuthenticateError)
        val cancellationSignal = CancellationSignal()

        biometricPrompt.authenticate(
            cancellationSignal,
            context.mainExecutor,
            getAuthenticationCallback(onAuthenticateSuccess, onAuthenticateError)
        )
    }
}


@RequiresApi(Build.VERSION_CODES.M)
private fun isBiometricSupported(context: Context): Boolean {
    val keyguardManager = context.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

    if (!keyguardManager.isDeviceSecure) return false

    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_BIOMETRIC)
        != PackageManager.PERMISSION_GRANTED
    ) return false

    return context.packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)
}

@RequiresApi(Build.VERSION_CODES.R)
private fun createBiometricPrompt(
    context: Context, onAuthenticateError: () -> Unit
): BiometricPrompt {
    return BiometricPrompt.Builder(context)
        .setAllowedAuthenticators(BIOMETRIC_STRONG)
        .setTitle(context.getString(R.string.biometric_promp_title))
        .setSubtitle(context.getString(R.string.biometric_promp_subtitle))
        .setConfirmationRequired(false)
        .setNegativeButton(
            context.getString(R.string.cancel_button),
            context.mainExecutor
        ) { _, _ -> onAuthenticateError() }
        .build()
}

@RequiresApi(Build.VERSION_CODES.P)
private fun getAuthenticationCallback(
    onAuthenticateSuccess: () -> Unit,
    onAuthenticateError: () -> Unit
): BiometricPrompt.AuthenticationCallback {
    return object : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
            super.onAuthenticationError(errorCode, errString)
            onAuthenticateError()
        }

        override fun onAuthenticationSucceeded(result: android.hardware.biometrics.BiometricPrompt.AuthenticationResult?) {
            super.onAuthenticationSucceeded(result)
            onAuthenticateSuccess()
        }
    }
}
