package com.coral.example.securenote.ui.models

data class NoteItem(
    val title: String,
    val message: String,
    val onEdit: () -> Unit,
    val onDelete: () -> Unit,
)