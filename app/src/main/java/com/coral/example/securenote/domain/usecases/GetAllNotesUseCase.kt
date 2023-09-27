package com.coral.example.securenote.domain.usecases

import com.coral.example.securenote.domain.repositories.IRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(private val repository: IRepository) {
    fun invoke() = repository.getAllNotes()
}