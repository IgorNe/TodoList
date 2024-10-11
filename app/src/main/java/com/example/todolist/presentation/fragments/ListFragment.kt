package com.example.todolist.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.data.Note
import com.example.todolist.databinding.ListFragmentBinding
import com.example.todolist.domain.models.ListFragmentViewModel
import com.example.todolist.domain.repositories.DataBaseRepositoryImpl
import com.example.todolist.presentation.adapters.ListFragmentRecyclerViewAdapter
import com.example.todolist.presentation.adapters.OnNoteEditListener
import com.example.todolist.presentation.dialogs.CreateNoteDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment: Fragment(), CreateNoteDialog.AddDialogListener, OnNoteEditListener {

    private var _binding: ListFragmentBinding? = null
    private val binding get() = _binding!!

    lateinit private var adapter :ListFragmentRecyclerViewAdapter

    lateinit var viewModel: ListFragmentViewModel
    lateinit var notesListObserver:Observer<List<Note>>




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ListFragmentViewModel(DataBaseRepositoryImpl(context = requireContext()))
        adapter = ListFragmentRecyclerViewAdapter(viewModel, this )
        _binding = ListFragmentBinding.inflate(layoutInflater)




        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()


    }


    private fun init(){
        binding.apply {
            rvListData.layoutManager = LinearLayoutManager(context)
            rvListData.adapter = adapter
            btnAddNote.setOnClickListener {
                activity?.supportFragmentManager?.let { it1 -> CreateNoteDialog(this@ListFragment, "Новая заметка", null).show(it1, "ReferenceDialog") }
            }
            notesListObserver = Observer { list ->
                adapter.updateData(list)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        @JvmStatic
        fun newInstance() = ListFragment()
    }


    override fun onStart() {
        super.onStart()
        viewModel.liveDataNotes.observe(this, notesListObserver)
    }

    override fun onStop() {
        super.onStop()
        viewModel.liveDataNotes.removeObserver(notesListObserver)
    }


    override fun noteDataUpdated(note: Note) {
        if (note.id == 0) {
            viewModel.addNote(note)
        } else {
            viewModel.updateNote(note)
        }
    }

    override fun onEditNoteRequested(note: Note) {
        activity?.supportFragmentManager?.let { it1 -> CreateNoteDialog(this@ListFragment, "Редактировать заметку", note).show(it1, "ReferenceDialog") }
    }


}