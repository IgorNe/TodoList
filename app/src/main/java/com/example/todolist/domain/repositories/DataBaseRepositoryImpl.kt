package com.example.todolist.domain.repositories

import android.content.Context
import com.example.todolist.data.Note
import com.example.todolist.database.DBManager
import com.example.todolist.domain.abstractions.DataBaseRepository

class DataBaseRepositoryImpl(context: Context):DataBaseRepository {

    val dbManager = DBManager(context)

    override fun loadTable():MutableList<Note>{
        return dbManager.loadTable()
    }

    override fun insertNote(note:Note):Long {
        return dbManager.addData(note)
    }

    override fun clearTable() : Int {
        return dbManager.clearTable()
    }
}