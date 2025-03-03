package com.example.noteappcompose.db

import com.example.noteappcompose.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(private var dao: NoteDao) {
    suspend fun addNote(note: Note){
        return dao.addNote(note)
    }
    suspend fun getNote():Flow<List<Note>>{
        return dao.getNote()
    }
    suspend fun getNoteId(id:String):Flow<Note>{
        return dao.getNoteId(id)
    }
    suspend fun updateNote(note: Note){
        return dao.updateNote(note)
    }
    suspend fun searchNote(query:String):Flow<List<Note>> {
        var searchQuery="%$query%"
        return dao.searchNote(searchQuery)
    }
    suspend fun deleteNote(id: String){
        return dao.deleteNote(id)
    }
}