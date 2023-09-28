package com.coral.example.securenote.domain.repositories

import com.coral.example.securenote.data.local.LocalDataSource
import com.coral.example.securenote.data.local.entities.NoteDTO
import com.coral.example.securenote.ui.models.NoteItem
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class RepositoryTest {

    private lateinit var repository: Repository
    private val localDataSource = mockk<LocalDataSource>(relaxed = true)

    @Before
    fun setUp() {
        repository = Repository(localDataSource)
    }

    @Test
    fun `test addNote calls storeNote on localDataSource`() = runBlockingTest {
        val note = NoteItem("1", "title", "message")

        coEvery { localDataSource.storeNote(note.toDto()) } returns Unit

        repository.addNote(note)

        coVerify { localDataSource.storeNote(note.toDto()) }
    }

    @Test
    fun `test deleteNote calls deleteNote on localDataSource`() = runBlockingTest {
        val id = "1"

        coEvery { localDataSource.deleteNote(id) } returns Unit

        repository.deleteNote(id)

        coVerify { localDataSource.deleteNote(id) }
    }

    @Test
    fun `test getAllNotes calls getAllNotes on localDataSource and converts to NoteItems`() =
        runBlockingTest {
            val notesDTO = listOf(
                NoteDTO("1", "title1", "message1"),
                NoteDTO("2", "title2", "message2")
            )

            coEvery { localDataSource.getAllNotes() } returns flowOf(notesDTO)

            val notesFlow = repository.getAllNotes()

            notesFlow.collect { notes ->
                assert(notes.size == 2)
                assert(notes[0].id == "1")
                assert(notes[0].title == "title1")
                assert(notes[0].message == "message1")
                assert(notes[1].id == "2")
                assert(notes[1].title == "title2")
                assert(notes[1].message == "message2")
            }

            coVerify { localDataSource.getAllNotes() }
        }
}
