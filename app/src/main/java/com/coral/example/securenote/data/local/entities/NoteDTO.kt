package com.coral.example.securenote.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "notes")
data class NoteDTO(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val message: String,
)
