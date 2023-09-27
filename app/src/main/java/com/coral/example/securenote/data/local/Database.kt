package com.coral.example.securenote.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.coral.example.securenote.data.local.entities.NoteDTO

@Database(entities = [NoteDTO::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}