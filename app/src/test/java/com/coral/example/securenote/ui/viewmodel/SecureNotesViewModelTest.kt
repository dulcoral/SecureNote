package com.coral.example.securenote.ui.viewmodel

import com.coral.example.securenote.domain.usecases.AddNoteUseCase
import com.coral.example.securenote.domain.usecases.DeleteNoteUseCase
import com.coral.example.securenote.domain.usecases.EditNoteUseCase
import com.coral.example.securenote.domain.usecases.GetAllNotesUseCase
import com.coral.example.securenote.ui.models.NoteItem
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.Before


@ExperimentalCoroutinesApi
class SecureNotesViewModelTest {

    private val getAllNotesUseCase: GetAllNotesUseCase = mockk()
    private val addNoteUseCase: AddNoteUseCase = mockk(relaxed = true)
    private val deleteNoteUseCase: DeleteNoteUseCase = mockk(relaxed = true)
    private val editNoteUseCase: EditNoteUseCase = mockk(relaxed = true)


    private lateinit var viewModel: SecureNotesViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        coEvery { getAllNotesUseCase.invoke() } returns flowOf(emptyList())
        coEvery { addNoteUseCase.invoke(any()) } just Runs
        coEvery { deleteNoteUseCase.invoke(any()) } just Runs
        coEvery { editNoteUseCase.invoke(any()) } just Runs

        viewModel = SecureNotesViewModel(
            getAllNotesUseCase,
            addNoteUseCase,
            deleteNoteUseCase,
            editNoteUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testAddingNewNote() = runBlockingTest {
        // Asume
        val expectedTitle = "Test"
        val expectedMessage = "Test Message"
        val noteCaptor = slot<NoteItem>()

        // Act
        viewModel.updateNoteTitle(expectedTitle)
        viewModel.updateNoteMessage(expectedMessage)
        viewModel.saveNote(false)

        // Assert
        coVerify { addNoteUseCase.invoke(capture(noteCaptor)) }
        assertEquals(expectedTitle, noteCaptor.captured.title)
        assertEquals(expectedMessage, noteCaptor.captured.message)
    }

    @Test
    fun testEditNote() = runBlockingTest {
        // Asume
        val expectedTitle = "Test Edit"
        val expectedMessage = "Test Message Edit"
        val noteCaptor = slot<NoteItem>()

        // Act
        viewModel.updateNoteTitle(expectedTitle)
        viewModel.updateNoteMessage(expectedMessage)
        viewModel.saveNote(true)

        // Assert
        coVerify { editNoteUseCase.invoke(capture(noteCaptor)) }
        assertEquals(expectedTitle, noteCaptor.captured.title)
        assertEquals(expectedMessage, noteCaptor.captured.message)
    }

    @Test
    fun testDeleteNote() = runBlockingTest {
        val noteId = "1"

        // Asume
        coEvery { deleteNoteUseCase.invoke(noteId) } returns Unit

        // Act
        viewModel.deleteNote(noteId)

        // Assert
        coVerify { deleteNoteUseCase.invoke(noteId) }
    }

    @Test
    fun testClearNote() = runBlockingTest {
        viewModel.updateNoteTitle("Test title")
        viewModel.updateNoteMessage("Test message")

        // Asume
        assertNotEquals("", viewModel.noteTitle)
        assertNotEquals("", viewModel.noteMessage)

        // Act
        viewModel.clearNoteFields()

        // Assert
        assertEquals("", viewModel.noteTitle)
        assertEquals("", viewModel.noteMessage)
    }

}
