package com.example.todolist.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.Note
import com.example.todolist.databinding.ItemDataBinding

class ListFragmentRecyclerViewAdapter: RecyclerView.Adapter<ListFragmentRecyclerViewAdapter.MyViewHolder>() {

    var noteList:  ArrayList<Note> = arrayListOf(
        Note(1, "Title 1", "Description 1", "01.10.2024"),
        Note(2, "Title 2", "Description 2", "02.10.2024"),
        Note(3, "Title 3", "Description 3", "03.10.2024"),
        Note(4, "Title 4", "Description 4", "04.10.2024"),
        Note(5, "Title 5", "Description 5", "05.10.2024"),
        Note(6, "Title 6", "Description 6", "06.10.2024"),
        Note(7, "Title 7", "Description 7", "07.10.2024"),
        Note(8, "Title 8", "Description 8", "08.10.2024"),
        Note(9, "Title 9", "Description 9", "09.10.2024"),
        Note(10, "Title 10", "Description 10", "10.10.2024"),
        Note(11, "Title 11", "Description 11", "11.10.2024"),
        Note(12, "Title 12", "Description 12", "12.10.2024"),
        Note(13, "Title 13", "Description 13", "13.10.2024"),
        Note(14, "Title 14", "Description 14", "14.10.2024"),
        Note(15, "Title 15", "Description 15", "15.10.2024")
    )

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemDataBinding.bind(view)

        fun bind(note: Note) = with(binding){
            tvId.text = note.id.toString()
            tvTitle.text = note.title
            tvDescription.text = note.description
            tvCreationTime.text = note.time
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_data, viewGroup, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {

        viewHolder.bind(noteList[position])
        viewHolder.itemView.setOnClickListener { println("Clicked>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Clicked") }
    }

    override fun getItemCount() = noteList.size

//    fun getallNotes(notes: ArrayList<Note>){
//        noteList
//    }

}