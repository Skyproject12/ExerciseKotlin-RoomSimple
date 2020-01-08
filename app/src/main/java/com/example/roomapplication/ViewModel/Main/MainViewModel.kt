package com.example.roomapplication.ViewModel.Main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.roomapplication.Data.Source.Model.Entity.Note
import com.example.roomapplication.Data.Source.Model.NoteRepository

class MainViewModel(application: Application) : ViewModel() {
    val mNoteRepository: NoteRepository = NoteRepository(application)
    // get All note in main
    fun getAllNotes(): LiveData<List<Note>> = mNoteRepository.getAllNotes()

}