package com.example.roomapplication.Data.Source.Model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomapplication.Data.Source.Model.Entity.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("SELECT * FROM note ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Note>>
}