package com.example.roomapplication.ViewModel.Insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.roomapplication.Data.Source.Model.Entity.Note
import com.example.roomapplication.Data.Source.Model.NoteRepository

class InsertViewModel(application: Application) : ViewModel() {
    // defination of repository
    private val mNoteRepository: NoteRepository = NoteRepository(application)

    // insert local
    fun insert(note: Note) {
        mNoteRepository.insert(note)
    }

    // update local
    fun update(note: Note) {
        mNoteRepository.update(note)
    }

    // delete local
    fun delete(note: Note) {
        mNoteRepository.delete(note)
    }
}