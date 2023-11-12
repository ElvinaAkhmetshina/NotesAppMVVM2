package ru.el.notesapp.database

import androidx.lifecycle.LiveData

interface DatabaseRepository {
    val readAll: LiveData<List<Note>>
     suspend fun create(note: Not, onSuccess: () -> Unit)

    suspend fun update(note: Not, onSuccess: () -> Unit)

    suspend fun delete(note: Not, onSuccess: () -> Unit)

}