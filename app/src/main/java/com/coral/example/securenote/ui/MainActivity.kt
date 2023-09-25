package com.coral.example.securenote.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.coral.example.securenote.ui.theme.SecureNoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecureNoteTheme {
                SecureNotesScreen()
            }
        }
    }
}
