package com.coral.example.securenote.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val _note = mutableStateOf(NoteItem(title = "", message = ""))
    val note: NoteItem get() = _note.value
    private val _noteTitle = mutableStateOf("")
    private val _noteMessage = mutableStateOf("")
    val noteTitle: String get() = _noteTitle.value
    val noteMessage: String get() = _noteMessage.value

    private val _listNotes = getAllNotesUseCase.invoke()
    val listNotes: Flow<List<NoteItem>> get() = _listNotes


    fun saveNote(isEdit: Boolean) {
        if (isEdit) {
            editNote(NoteItem(id = note.id, title = noteTitle, message = noteMessage))
        } else {
            addNewNote()
        }
    }

    private fun addNewNote() {
        viewModelScope.launch {
            addNoteUseCase.invoke(NoteItem(title = noteTitle, message = noteMessage))
            clearNoteFields()
        }
    }

    private fun editNote(note: NoteItem) {
        viewModelScope.launch {
            editNoteUseCase.invoke(note)
            clearNoteFields()
        }
    }

    fun deleteNote(id: String) {
        viewModelScope.launch {
            deleteNoteUseCase.invoke(id)
        }
    }

    fun updateNote(note: NoteItem) {
        _note.value = note
        _noteTitle.value = note.title
        _noteMessage.value = note.message
    }

    fun updateNoteTitle(title: String) {
        _noteTitle.value = title
    }

    fun updateNoteMessage(message: String) {
        _noteMessage.value = message
    }

    private fun clearNoteFields() {
        _noteTitle.value = ""
        _noteMessage.value = ""
        _note.value = NoteItem(title = "", message = "")
    }
}