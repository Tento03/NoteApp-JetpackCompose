package com.example.noteappcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.noteappcompose.ui.theme.NoteAppComposeTheme
import com.example.noteappcompose.uiux.NoteCreate
import com.example.noteappcompose.uiux.NoteDelete
import com.example.noteappcompose.uiux.NoteRead
import com.example.noteappcompose.uiux.NoteSearch
import com.example.noteappcompose.uiux.NoteUpdate
import dagger.hilt.android.AndroidEntryPoint

@Suppress("UNREACHABLE_CODE")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteAppComposeTheme {
                val navController= rememberNavController()
                var isSearching by remember {
                    mutableStateOf(false)
                }
                var query by remember {
                    mutableStateOf("")
                }
                val keyboardController= LocalSoftwareKeyboardController.current

                Scaffold(
                    topBar = {
                        if (isSearching){
                            TopAppBar(
                                title = {
                                    OutlinedTextField(
                                        value = query,
                                        onValueChange = {
                                            query=it
                                        },
                                        label = { Text("Search") },
                                        placeholder = { Text("Search Here") },
                                        modifier = Modifier.padding(top=20.dp),
                                        keyboardOptions = KeyboardOptions.Default.copy(
                                            imeAction = ImeAction.Search
                                        ),
                                        keyboardActions = KeyboardActions(
                                            onSearch = {
                                                navController.navigate("Search/{query}")
                                            }
                                        )
                                    )
                                },
                                actions = {
                                    IconButton(onClick = {isSearching=false}) {
                                        Icon(imageVector = Icons.Default.Close,contentDescription = null)
                                    }
                                },
                                modifier = Modifier.padding(bottom = 20.dp)
                            )
                        }
                        else{
                            TopAppBar(
                                title = { Text("Notes App by Tento") },
                                actions = {
                                    IconButton(onClick = {isSearching=true}) {
                                        Icon(imageVector = Icons.Default.Search,contentDescription = null)
                                    }
                                },
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = Color.Green,
                                    titleContentColor = Color.White
                                ),
                                modifier = Modifier.padding(bottom = 20.dp)
                            )
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {navController.navigate("Create")},
                            containerColor = Color.Green,
                            contentColor = Color.White,
                        ) {
                            Icon(Icons.Filled.Add,null)
                        }
                    }
                ) {
                    NavHost(navController = navController, startDestination = "Read"){
                        composable(route = "Read"){
                            NoteRead(navController)
                        }
                        composable(route = "Create"){
                            NoteCreate(navController)
                        }
                        composable(route = "Update/{id}",
                            arguments = listOf(navArgument("id",{
                                type= NavType.StringType
                            }))
                        ){
                            var id=it.arguments?.getString("id")
                            if (id != null) {
                                NoteUpdate(navController,id)
                            }
                        }
                        composable(route = "Delete/{id}",
                            arguments = listOf(navArgument("id",{
                                type= NavType.StringType
                            }))
                        ){
                            var id=it.arguments?.getString("id")
                            if (id != null) {
                                NoteDelete(navController,id)
                            }
                        }
                        composable(route="Search/{query}",
                            arguments = listOf(navArgument("query",{
                                type= NavType.StringType
                            }))
                        ){
                            val query=it.arguments?.getString("query")
                            if (query != null) {
                                NoteSearch(navController,query)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteAppComposeTheme {
        Greeting("Android")
    }
}