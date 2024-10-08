package com.example.todolist.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.todolist.domain.models.ListFragmentModel

class ListFragment: Fragment() {


    lateinit var viewModel: ListFragmentModel





    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        viewModel = ListFragmentModel()
        var jkhgjh = viewModel.liveDataNotes









        return super.onCreateView(inflater, container, savedInstanceState)
    }


}