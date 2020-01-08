package com.example.roomapplication.Data.Source.Model

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.roomapplication.Data.Source.Model.Database.NoteDatabase
import com.example.roomapplication.Data.Source.Model.Entity.Note
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {
    private val mNoteDao: NoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    // initial noteDao
    init {
        val db = NoteDatabase.getDatabase(application)
        mNoteDao = db.noteDao()
    }

    // get all data use live data
    fun getAllNotes(): LiveData<List<Note>> = mNoteDao.getAllNotes()
    // insert all note
    fun insert(note: Note) {
        executorService.execute {
            mNoteDao.insert(note)
        }
    }

    // delete the note
    fun delete(note: Note) {
        executorService.execute {
            mNoteDao.delete(note)
        }
    }

    // udpate note
    fun update(note: Note) {
        executorService.execute {
            mNoteDao.update(note)
        }
    }
}