package com.coral.example.securenote.domain.repositories

import com.coral.example.securenote.data.local.LocalDataSource
import com.coral.example.securenote.data.local.entities.NoteDTO
import com.coral.example.securenote.ui.models.NoteItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Repository @Inject constructor(private val localDataSource: LocalDataSource) : IRepository {
    override suspend fun addNote(note: NoteItem) {
        localDataSource.storeNote(note.toDto())
    }

    override suspend fun deleteNote(id: String) {
        localDataSource.deleteNote(id)
    }

    override fun getAllNotes(): Flow<List<NoteItem>> {
        return localDataSource.getAllNotes()
            .map { notesDTO ->
                notesDTO.map { noteDTO ->
                    convertToNoteItem(noteDTO)
                }
            }

    }

    private fun convertToNoteItem(noteDTO: NoteDTO): NoteItem {
        return NoteItem(
            id = noteDTO.id,
            title = noteDTO.title,
            message = noteDTO.message,
        )
    }

}