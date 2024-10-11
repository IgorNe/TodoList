package com.example.todolist.domain.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.data.Note
import com.example.todolist.domain.abstractions.DataBaseRepository
import com.example.todolist.domain.usecases.InsertNoteUseCase
import com.example.todolist.domain.usecases.LoadTableUseCase
import com.example.todolist.domain.usecases.RemoveNoteUseCase
import com.example.todolist.domain.usecases.UpdateNoteUseCase
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.random.Random

class ListFragmentViewModel(val dataBaseRepository: DataBaseRepository) :ViewModel() {

    private val _notes = MutableLiveData<MutableList<Note>>()
    val liveDataNotes : LiveData<MutableList<Note>> = _notes

    val insertNoteUseCase = InsertNoteUseCase(dataBaseRepository)
    val updateNoteUseCase = UpdateNoteUseCase(dataBaseRepository)
    val removeNoteUseCase = RemoveNoteUseCase(dataBaseRepository)
    val loadTableUseCase = LoadTableUseCase(dataBaseRepository)


    init {
        _notes.value = loadTableUseCase.execute()
    }


    fun addNote(note: Note) {
        note.time = SimpleDateFormat("HH:mm:ss dd.MM.yy").format(Date())
        insertNoteUseCase.execute(note)
        _notes.value = loadTableUseCase.execute()
    }
    fun deleteNote(note: Note) {
        removeNoteUseCase.execute(note)
        _notes.value = loadTableUseCase.execute()
    }
    fun updateNote(note: Note) {
        note.time = SimpleDateFormat("HH:mm:ss dd.MM.yy").format(Date())
        updateNoteUseCase.execute(note)
        _notes.value = loadTableUseCase.execute()
    }


}