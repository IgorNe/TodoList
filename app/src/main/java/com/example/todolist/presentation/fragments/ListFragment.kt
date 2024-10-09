package com.example.todolist.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.databinding.ListFragmentBinding
import com.example.todolist.domain.models.ListFragmentModel
import com.example.todolist.presentation.adapters.ListFragmentRecyclerViewAdapter

class ListFragment: Fragment() {

    private var _binding: ListFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = ListFragmentRecyclerViewAdapter()

    lateinit var viewModel: ListFragmentModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ListFragmentModel()
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

}