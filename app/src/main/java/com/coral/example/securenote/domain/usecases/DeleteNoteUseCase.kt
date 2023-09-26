package com.coral.example.securenote.domain.usecases

import com.coral.example.securenote.domain.repositories.IRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(private val repository: IRepository) {
}