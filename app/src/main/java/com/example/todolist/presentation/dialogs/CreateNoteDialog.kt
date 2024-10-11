package com.example.todolist.presentation.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.todolist.R
import com.example.todolist.data.Note

class CreateNoteDialog(val dialogListener:AddDialogListener, val dialogTitle:String, var note: Note?): DialogFragment() {

    interface AddDialogListener{
        fun noteDataUpdated(note: Note)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.add_note_dialog, null)

        var editTextTitle = view.findViewById<EditText>(R.id.et_title)
        var editTextDescription = view.findViewById<EditText>(R.id.et_description)


        if(note != null){
            editTextTitle.setText(note!!.title)
            editTextDescription.setText(note!!.description)


            return AlertDialog.Builder(requireContext())
                .setTitle(dialogTitle)
                .setView(view)
                .setPositiveButton("Сохранить") { _, _ ->


                    dialogListener.noteDataUpdated(Note(
                        title = editTextTitle.text.toString(),
                        description = editTextDescription.text.toString(),
                        id = note!!.id,
                        time = ""))
                }
                .setNegativeButton("Отмена", null)
                .create()
        }

        return AlertDialog.Builder(requireContext())
            .setTitle(dialogTitle)
            .setView(view)
            .setPositiveButton("Сохранить") { _, _ ->


                dialogListener.noteDataUpdated(Note(
                    title = editTextTitle.text.toString(),
                    description = editTextDescription.text.toString(),
                    id = 0,
                    time = ""))
            }
            .setNegativeButton("Отмена", null)
            .create()
    }

}