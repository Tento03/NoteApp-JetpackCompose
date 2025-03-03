package com.example.noteappcompose.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappcompose.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private var repository: NoteRepository):ViewModel() {
    private var _getNote=MutableStateFlow<List<Note>>(emptyList())
    var getNote:MutableStateFlow<List<Note>> = _getNote

    private var _getNoteId=MutableStateFlow<Note?>(null)
    var getNoteId:MutableStateFlow<Note?> = _getNoteId

    private var _searchNote=MutableStateFlow<List<Note>>(emptyList())
    var searchNote:MutableStateFlow<List<Note>> = _searchNote

    fun addNote(note: Note){
        try {
            viewModelScope.launch(Dispatchers.IO) {
                var response=repository.addNote(note)
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun getNote(){
        try {
            viewModelScope.launch {
                var response=repository.getNote().collect{
                    _getNote.value=it
                }
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun getNoteId(id:String){
        try {
            viewModelScope.launch {
                var response=repository.getNoteId(id).collect{
                    _getNoteId.value=it
                }
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun updateNote(note: Note){
        try {
            viewModelScope.launch(Dispatchers.IO) {
                repository.updateNote(note)
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun searchNote(query:String){
        try {
            viewModelScope.launch {
                var response=repository.searchNote(query).collect{
                    _searchNote.value=it
                }
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun deleteNote(id: String){
        try {
            viewModelScope.launch(Dispatchers.IO) {
                repository.deleteNote(id)
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }
}