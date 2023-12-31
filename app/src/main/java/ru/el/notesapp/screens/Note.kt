package ru.el.notesapp.screens

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import ru.el.notesapp.MainViewModel
import ru.el.notesapp.MainViewModelFactory
import ru.el.notesapp.model.Note
import ru.el.notesapp.navigation.NavRoute
import ru.el.notesapp.ui.theme.NotesAppTheme
import ru.el.notesapp.utils.Constants


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteScreen(navController: NavHostController, noteId: String?, viewModel: MainViewModel){
    val notes = viewModel.readAllNotes().observeAsState(listOf()).value
    val note = notes.firstOrNull{it.id == noteId?.toInt()}?: Note(title=Constants.Keys.NONE, subtitle = Constants.Keys.NONE)
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var title by remember { mutableStateOf(Constants.Keys.EMPTY) }
    var subtitle by remember { mutableStateOf(Constants.Keys.EMPTY) }
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
        Surface{
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 32.dp)
            ){
                Text(text = Constants.Keys.EDIT_NOTE,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                OutlinedTextField(
                    value = title,
                    onValueChange = {title = it},
                    label = {Text(text=Constants.Keys.TITLE)},
                    isError = title.isEmpty()
                )
                OutlinedTextField(
                    value = subtitle,
                    onValueChange = {subtitle = it},
                    label = {Text(text=Constants.Keys.SUBTITLE)},
                    isError = subtitle.isEmpty()
                )
                Button(
                    modifier = Modifier.padding(top=16.dp),
                    onClick = { viewModel.updateNote(note= Note(id=note.id, title =title, subtitle = subtitle)){navController.navigate(NavRoute.Main.route)} }) {
                    Text(text = Constants.Keys.UPDATE_NOTE)

                }
            }
        }

        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            HorizontalAlignment = Alignment.CenterHorizontally,
            VerticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                )
                {
                    Text(
                        text = note.title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 32.dp)
                    )
                    Text(
                        text = note.subtitle,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.light,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }

            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = { coroutineScope.launch {
                    title = note.title
                    subtitle = note.subtitle
                    bottomSheetState.show()
                } }) {
                    Text(text = Constants.Keys.UPDATE)

                }

                Button(onClick = { viewModel.deleteNote(note=note){navController.navigate(NavRoute.Main.route)} }) {
                   // Text(text = Constants.Keys.DELETE)
                }
                Button(modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),

                    onClick = { navController.navigate(NavRoute.Main.route) }) {

                    Text(text = Constants.Keys.NAV_BACK)

                }
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
        NoteScreen(
            navController = rememberNavController(),
            viewModel = mViewModel,
            noteId = "1"
        )
    }
}