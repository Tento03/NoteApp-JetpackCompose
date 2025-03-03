package com.example.noteappcompose.uiux

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noteappcompose.db.NoteViewModel
import com.example.noteappcompose.ui.theme.NoteAppComposeTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteRead(navController: NavController, viewModel: NoteViewModel = hiltViewModel()) {
    val getNote by viewModel.getNote.collectAsState()
    viewModel.getNote()

    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.padding(top = 100.dp, start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(getNote) { item ->
            NoteCard(
                title = item.title,
                content = item.content,
                onEdit = {
                    navController.navigate("Update/${item.id}")
                    viewModel.updateNote(item)
                },
                onDelete = {
                    viewModel.deleteNote(id = item.id)
                    Toast.makeText(context, "Note Deleted", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}

@Composable
fun NoteCard(title: String, content: String, onEdit: () -> Unit, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),  // Mengisi seluruh lebar layar
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(16.dp)  // Mengatur sudut yang lebih lembut
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),  // Menambah padding dalam Card
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = { onEdit() }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        modifier = Modifier.size(24.dp)
                    )
                }

                IconButton(onClick = { onDelete() }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = content,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NoteCardPreview() {
    NoteAppComposeTheme {
        NoteCard(
            title = "Sample Title",
            content = "This is a sample content for the note. Here you can put any text or information related to the note.",
            onEdit = {},
            onDelete = {}
        )
    }
}