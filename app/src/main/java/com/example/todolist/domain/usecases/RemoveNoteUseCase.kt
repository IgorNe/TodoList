package com.example.todolist.domain.usecases

import com.example.todolist.data.Note
import com.example.todolist.domain.abstractions.DataBaseRepository

class RemoveNoteUseCase(val dataBaseRepository: DataBaseRepository) {
    fun execute(note: Note): Int{
        return dataBaseRepository.removeNote(note)
    }
}