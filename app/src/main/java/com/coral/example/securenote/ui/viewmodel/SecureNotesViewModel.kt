package com.coral.example.securenote.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coral.example.securenote.domain.repositories.IRepository
import com.coral.example.securenote.domain.usecases.AddNoteUseCase
import com.coral.example.securenote.domain.usecases.DeleteNoteUseCase
import com.coral.example.securenote.domain.usecases.EditNoteUseCase
import com.coral.example.securenote.domain.usecases.GetAllNotesUseCase
import com.coral.example.securenote.ui.models.NoteItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecureNotesViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase,
) : ViewModel() {

    fun addNewNote() {
        viewModelScope.launch {
            addNoteUseCase.invoke(NoteItem(title = "", message = "noteMessage"))
        }

    }

}