package com.example.todolist.domain.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.data.Note

class ListFragmentModel :ViewModel() {

    private val _notes = MutableLiveData<HashMap<Int, Note>>()
    val liveDataNotes : LiveData<HashMap<Int, Note>> = _notes

}