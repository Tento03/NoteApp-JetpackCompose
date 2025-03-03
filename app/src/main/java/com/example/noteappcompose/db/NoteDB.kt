package com.example.noteappcompose.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteappcompose.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDB:RoomDatabase() {
    abstract fun NoteDao():NoteDao
}