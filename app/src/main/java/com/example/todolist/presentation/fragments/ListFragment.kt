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
import com.example.todolist.presentation.adapters.ListFragmentRecyclerViewAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment: Fragment() {

    private var _binding: ListFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = ListFragmentRecyclerViewAdapter()

    lateinit var viewModel: ListFragmentViewModel
    lateinit var notesListObserver:Observer<List<Note>>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ListFragmentViewModel()
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
                viewModel.addNote()
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




}