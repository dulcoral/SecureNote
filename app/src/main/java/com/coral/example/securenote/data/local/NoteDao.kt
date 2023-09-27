package com.coral.example.securenote.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.coral.example.securenote.data.local.entities.NoteDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun fetchAllNotes(): Flow<List<NoteDTO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeNote(note: NoteDTO)

    @Query("DELETE FROM notes WHERE id =:id")
    suspend fun deleteNoteById(id: String)
}

