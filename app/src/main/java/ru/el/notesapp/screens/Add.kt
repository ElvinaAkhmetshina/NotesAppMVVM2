package ru.el.notesapp.screens

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.el.notesapp.MainViewModel
import ru.el.notesapp.MainViewModelFactory
import ru.el.notesapp.ui.theme.NotesAppTheme


@Composable
fun AddScreen(navController: NavHostController, viewModel: MainViewModel){
    var title by remember{mutableStateOf("")}
    var subtitle by remember{mutableStateOf("")}
    var isButtonEnabled by remember{ mutableStateOf(false) }
    Scaffold{
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(
                text = "Add",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            OutlinedTextField(value = title, onValueChange = {
                title =it
                isButtonEnabled=title.isNotEmpty() && subtitle.isNotEmpty()}, label = {Text(text="Note title")}, isError = title.isEmpty())
            OutlinedTextField(value = subtitle, onValueChange = {
                subtitle =it
                isButtonEnabled=title.isNotEmpty() && subtitle.isNotEmpty()  }, label = {Text(text="Note subtitle")}, isError = title.isEmpty())
            Button(
                modifier = Modifier.padding(top = 16.dp),
                enabled = isButtonEnabled,
                onClick = {
                    viewModel.addNote(note = Note(title = title, subtitle = subtitle)) {
                        navController.navigate(NavRoute.Main.route)
                    }
                }
            )
            {
                Text(text = "Add")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevAddScreen(){
    NotesAppTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        val notes = mViewModel.readTest.observeAsState(listOf()).value
        AddScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}