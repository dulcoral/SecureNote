package com.coral.example.securenote.data.local

import com.coral.example.securenote.data.IDataSource
import com.coral.example.securenote.data.local.entities.NoteDTO
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val noteDao: NoteDao) : IDataSource {
    override suspend fun storeNote(note: NoteDTO) = noteDao.storeNote(note)

    override suspend fun deleteNote(id: String) = noteDao.deleteNoteById(id)

    override fun getAllNotes() = noteDao.fetchAllNotes()
}