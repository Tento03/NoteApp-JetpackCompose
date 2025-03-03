package com.example.noteappcompose.uiux

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noteappcompose.db.NoteViewModel

@Composable
fun NoteDelete(navController: NavController,id:String,viewModel: NoteViewModel= hiltViewModel()) {
    val getNoteId by viewModel.getNoteId.collectAsState()

    viewModel.getNoteId(id)
    val idNote= getNoteId?.id
    if (idNote != null) {
        viewModel.deleteNote(idNote)
    }
    navController.navigate("Read"){
        popUpToRoute
    }
}