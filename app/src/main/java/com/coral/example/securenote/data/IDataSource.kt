package com.coral.example.securenote.data

import com.coral.example.securenote.data.local.entities.NoteDTO
import kotlinx.coroutines.flow.Flow

interface IDataSource {
    suspend fun storeNote(note: NoteDTO)

    suspend fun deleteNote(id: String)

    fun getAllNotes(): Flow<List<NoteDTO>>
}