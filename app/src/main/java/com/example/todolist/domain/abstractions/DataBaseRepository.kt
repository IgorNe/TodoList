package com.example.todolist.domain.abstractions

import com.example.todolist.data.Note
import com.example.todolist.database.DBManager

interface DataBaseRepository {
    fun loadTable():MutableList<Note>
    fun insertNote(note:Note):Long
    fun clearTable(): Int
}