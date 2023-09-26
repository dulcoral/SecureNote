package com.coral.example.securenote.domain.usecases

import com.coral.example.securenote.domain.repositories.IRepository
import com.coral.example.securenote.ui.models.NoteItem
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(private val repository: IRepository) {

    fun invoke(note: NoteItem) = repository.addNote(note)
}