package ru.el.notesapp.screens
import android.app.Application
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.el.notesapp.MainViewModel
import ru.el.notesapp.MainViewModelFactory
import ru.el.notesapp.ui.theme.NotesAppTheme


@Composable
fun MainScreen(navController: NavHostController){
    val notes = viewModel.readAllNotes().observeAsState(listOf()).value
    //val context = LocalContext.current
    //val mViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavRoute.Add.route)
                }
            ){
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add", tint = color.White)
            }
        }
    )
    {
        /*Column(
            NoteItem(title = "Note1", subtitle = "Subt ", navController = navController),
            NoteItem(title = "Note1", subtitle = "Subt ", navController = navController),
            NoteItem(title = "Note1", subtitle = "Subt ", navController = navController),
            NoteItem(title = "Note1", subtitle = "Subt ", navController = navController)
        )*/
       LazyColumn{
            items(notes) {
                note->
                NoteItem(note = note, navController = navController)
            }
        }
    }
}

@Composable
fun NoteItem(note: Note, navController: NavHostController)
{
Card(
modifier = Modifier
.fillMaxWidth()
.padding(vertical = 8.dp, horizontal =24.dp)
.clickable {
    navController.navidate(NavRroute.Note.route+"/${note.id}")
},
elevation = 6.dp
)
{
    Column(
        modifier = Modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = note.title,
            fontsize = 24.sp,
            fontWeight.Bold
        )
        Text(
            text = note.subtitle
        )
    }

}
}


@Preview(showBackground = true)
@Composable
fun prevMainScreen(){
    NotesAppTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        val notes = mViewModel.readTest.observeAsState(listOf()).value
        MainScreen(navController = rememberNavController())
    }
}