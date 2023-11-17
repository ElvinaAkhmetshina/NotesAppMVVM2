package ru.el.notesapp.utils

import ru.el.notesapp.database.DatabaseRepository
import ru.el.notesapp.navigation.NavRoute

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"

lateinit var REPOSITORY: DatabaseRepository

object Constants {
    object Keys{
        const val NOTE_DATABASE = "notes_database"
        const val NOTES_TABLE = "notes_table"
        const val ADD_NEW_NOTE = "notes_table"
        const val NOTE_TITLE = "notes_table"
        const val NOTE_SUBTITLE = "notes_table"
        const val ADD_NOTE = "notes_table"
        const val TITLE = "notes_table"
        const val SUBTITLE = "notes_table"
        const val WHAT_WILL_WE_USE = "notes_table"
        const val ROOM_DATABASE = "notes_table"
        const val FIREBASE_SATABASE = "notes_table"
        const val ID= "id"
        const val NONE= "none"
        const val EMPTY = ""
        const val EDIT_NOTE = ""
        const val UPDATE_NOTE = ""
        const val UPDATE = ""
        const val NAV_BACK = ""
    }

    object Screens{
        const val START_SCREEN="start_screen"
        const val MAIN_SCREEN=("main_screen")
        const val ADD_SCREEN=("add_screen")
        const val NOTE_SCREEN=("note_screen")
    }
}