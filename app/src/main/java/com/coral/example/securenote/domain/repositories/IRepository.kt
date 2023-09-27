package com.coral.example.securenote.domain.repositories

import com.coral.example.securenote.ui.models.NoteItem
import kotlinx.coroutines.flow.Flow

interface IRepository {

    suspend fun addNote(note: NoteItem)

    suspend fun deleteNote(id: String)

    fun getAllNotes(): Flow<List<NoteItem>>
}