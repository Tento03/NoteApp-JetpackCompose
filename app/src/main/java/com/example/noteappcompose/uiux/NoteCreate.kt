package com.example.noteappcompose.uiux

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noteappcompose.db.NoteViewModel
import com.example.noteappcompose.model.Note
import java.util.UUID

@Composable
fun NoteCreate(navController: NavController,viewModel: NoteViewModel= hiltViewModel()) {
    var title by remember {
        mutableStateOf("")
    }
    var content by remember {
        mutableStateOf("")
    }
    val context= LocalContext.current.applicationContext
    Column(modifier = Modifier.padding(top=100.dp, start = 20.dp, end = 20.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = title,
            onValueChange = {
                title=it
            },
            placeholder = { Text("Title") },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = content,
            onValueChange = {
                content=it
            },
            placeholder = { Text("Content") },
            label = { Text("Content") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp).height(200.dp),
            maxLines = 10
        )
        Button(
            onClick = {
                if (title.isNotEmpty() && content.isNotEmpty()){
                    var id=UUID.randomUUID().toString()
                    viewModel.addNote(Note(id = id,title=title,content=content))
                    Toast.makeText(context,"Note Added",Toast.LENGTH_SHORT).show()
                    title=""
                    content=""
                    navController.navigate("Read")
                }
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("ADD")
        }
    }
}