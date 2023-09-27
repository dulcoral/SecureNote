package com.coral.example.securenote.domain.repositories

import com.coral.example.securenote.data.local.entities.NoteDTO
import com.coral.example.securenote.ui.models.NoteItem

fun NoteItem.toDto(): NoteDTO {
    val item = this
    return NoteDTO(title = item.title, message = item.message)
}
