package com.coral.example.securenote.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.coral.example.securenote.R
import com.coral.example.securenote.ui.components.BiometricPrompt

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun AuthScreen(onSuccessAuth: () -> Unit) {
    val showPrompt = remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                Icons.Default.Face,
                contentDescription = stringResource(R.string.add_note_message_hint),
                modifier = Modifier.size(dimensionResource(R.dimen.icon_size))
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.size_medium)))
            Text(
                stringResource(R.string.auth_required),
                fontSize = 16.sp,
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.size_small)))
            Text(
                stringResource(R.string.app_name),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Button(onClick = { showPrompt.value = false }) {
                Text(stringResource(R.string.biometric_button))
            }

            if (!showPrompt.value) {
                BiometricPrompt(
                    promptShown = showPrompt,
                    onSuccessAuth = {
                        onSuccessAuth()
                    }
                )
            }
        }
    }
}
