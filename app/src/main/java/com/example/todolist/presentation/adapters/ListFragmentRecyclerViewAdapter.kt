package com.example.todolist.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.Note
import com.example.todolist.databinding.ItemDataBinding
import com.example.todolist.domain.models.ListFragmentViewModel
import com.example.todolist.presentation.fragments.ListFragment

class ListFragmentRecyclerViewAdapter(val viewModel: ListFragmentViewModel): RecyclerView.Adapter<ListFragmentRecyclerViewAdapter.MyViewHolder>() {

    var noteList:  List<Note> = arrayListOf()


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemDataBinding.bind(view)

        fun bind(note: Note) = with(binding){
            tvId.text = note.id.toString()
            tvTitle.text = note.title
            tvDescription.text = note.description
            tvCreationTime.text = note.time
            imgBtnEdit.setOnClickListener {

            }
            imgBtnDelete.setOnClickListener {
                viewModel.deleteNote(note)
            }
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


    fun updateData(newData: List<Note>) {
        noteList = newData
        notifyDataSetChanged()
    }


}