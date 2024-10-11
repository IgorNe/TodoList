package com.example.todolist.presentation.adapters

import com.example.todolist.data.Note

 interface OnNoteEditListener {
    fun onEditNoteRequested(note: Note)
}