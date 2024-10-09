package com.example.todolist.domain.usecases

import com.example.todolist.data.Note
import com.example.todolist.domain.abstractions.DataBaseRepository

class InsertNoteUseCase(val dataBaseRepository: DataBaseRepository) {
    fun execute(note: Note):Long{
        return dataBaseRepository.insertNote(note)
    }
}