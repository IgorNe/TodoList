package com.example.todolist.domain.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.data.Note
import com.example.todolist.domain.abstractions.DataBaseRepository
import com.example.todolist.domain.usecases.InsertNoteUseCase
import com.example.todolist.domain.usecases.LoadTableUseCase
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.random.Random

class ListFragmentViewModel(val dataBaseRepository: DataBaseRepository) :ViewModel() {

    private val _notes = MutableLiveData<MutableList<Note>>()
    val liveDataNotes : LiveData<MutableList<Note>> = _notes

    val insertNoteUseCase = InsertNoteUseCase(dataBaseRepository)
    val loadTableUseCase = LoadTableUseCase(dataBaseRepository)


    init {
        _notes.value = loadTableUseCase.execute()

    }


    fun addNote(note: Note) {
        note.time = SimpleDateFormat("HH:mm:ss dd.MM.yy").format(Date())
        insertNoteUseCase.execute(note)
        _notes.value = loadTableUseCase.execute()
/*        var v =_notes.value
        v?.add(Note(2, "qwe", "qweqwe", "09.10"))
        _notes.value = v*/
    }




}