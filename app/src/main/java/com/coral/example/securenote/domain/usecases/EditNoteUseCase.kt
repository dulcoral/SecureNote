package com.coral.example.securenote.domain.usecases

import com.coral.example.securenote.domain.repositories.IRepository
import com.coral.example.securenote.ui.models.NoteItem
import javax.inject.Inject

class EditNoteUseCase @Inject constructor(private val repository: IRepository) {
    suspend fun invoke(note: NoteItem) = repository.addNote(note)
}