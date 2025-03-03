package com.example.noteappcompose.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.noteappcompose.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert
    fun addNote(note: Note)

    @Query("SELECT * FROM noteAppCompose")
    fun getNote():Flow<List<Note>>

    @Query("SELECT * FROM noteAppCompose WHERE id=:id")
    fun getNoteId(id:String):Flow<Note>

    @Update(entity = Note::class)
    fun updateNote(note: Note)

    @Query("SELECT * FROM noteAppCompose WHERE title LIKE :query OR content LIKE :query")
    fun searchNote(query:String):Flow<List<Note>>

    @Query("DELETE FROM noteAppCompose WHERE id=:id")
    fun deleteNote(id: String)
}
