package com.example.todolist.domain.abstractions

import com.example.todolist.data.Note
import com.example.todolist.database.DBManager

interface DataBaseRepository {
    fun loadTable():MutableList<Note>
    fun insertNote(note:Note):Long
    fun updateNote(note:Note):Int
    fun removeNote(note:Note):Int
    fun clearTable(): Int
}