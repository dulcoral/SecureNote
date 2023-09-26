package com.coral.example.securenote.domain.repositories

import com.coral.example.securenote.ui.models.NoteItem
import kotlinx.coroutines.flow.Flow

interface IRepository {

    fun addNote(note: NoteItem)

    fun editNote(id: String)

    fun deleteNote(id: String)

    fun getAllNotes(): Flow<List<NoteItem>>


}