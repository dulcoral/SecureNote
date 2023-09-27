package com.coral.example.securenote.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteDTO(
    @PrimaryKey
    val id: String,
    val title: String,
    val message: String,
)
