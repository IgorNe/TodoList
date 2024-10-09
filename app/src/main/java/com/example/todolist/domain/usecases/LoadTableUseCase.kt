package com.example.todolist.domain.usecases

import com.example.todolist.data.Note
import com.example.todolist.domain.abstractions.DataBaseRepository

class LoadTableUseCase(val dataBaseRepository: DataBaseRepository) {
    fun execute():MutableList<Note>{
        return dataBaseRepository.loadTable()
    }
}