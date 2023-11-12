package ru.el.notesapp.screens

import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.el.notesapp.MainViewModel
import ru.el.notesapp.MainViewModelFactory
import ru.el.notesapp.ui.theme.NotesAppTheme


@Composable
fun NoteScreen(navController: NavHostController){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.centerHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ){
            Column(
                modifier = Modifier.padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            )
            {
                Text(text = "Title",
                    fontSize = 24.sp,
                    fontWeight = fontWeight.Bold,
                    modifier = Modifier.padding(top=32.dp)
                    )
                Text(text = "subt",
                        fontSize = 18.sp,
                    fontWeight = fontWeight.light,
                    modifier = Modifier.padding(top=16.dp))
            }
        }
    }

}



@Preview(showBackground = true)
@Composable
fun prevNoteScreen(){
    NotesAppTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        val notes = mViewModel.readTest.observeAsState(listOf()).value
        NoteScreen(navController = rememberNavController())
    }
}