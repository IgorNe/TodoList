package com.example.todolist.domain.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.data.Note
import kotlin.random.Random

class ListFragmentViewModel :ViewModel() {

    private val _notes = MutableLiveData<MutableList<Note>>()
    val liveDataNotes : LiveData<MutableList<Note>> = _notes

    init {
        _notes.value = mutableListOf()

    }


    fun addNote() {
        var v =_notes.value
        v?.add(Note(2, "qwe", "qweqwe", "09.10"))
        _notes.value = v
    }




}