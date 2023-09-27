package com.coral.example.securenote.ui.models

import java.util.UUID

data class NoteItem(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val message: String
)