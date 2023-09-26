package com.coral.example.securenote.domain.repositories

import com.coral.example.securenote.data.local.LocalDataSource
import com.coral.example.securenote.ui.models.NoteItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(private val localDataSource: LocalDataSource): IRepository {
    override fun addNote(note: NoteItem) {
        TODO("Not yet implemented")
    }

    override fun editNote(id: String) {
        TODO("Not yet implemented")
    }

    override fun deleteNote(id: String) {
        TODO("Not yet implemented")
    }

    override fun getAllNotes(): Flow<List<NoteItem>> {
        TODO("Not yet implemented")
    }

}