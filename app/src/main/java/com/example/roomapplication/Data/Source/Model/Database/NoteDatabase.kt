package com.example.roomapplication.Data.Source.Model.Database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomapplication.Data.Source.Model.NoteDao

abstract class NoteDatabase : RoomDatabase() {
    // defination dao
    abstract fun noteDao(): NoteDao

    companion object {
        // defination instance for NoteDatabase
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        // make function to getDatabase rooom
        @JvmStatic
        fun getDatabase(context: Context): NoteDatabase {
            if (INSTANCE == null) {
                synchronized(NoteDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            NoteDatabase::class.java,
                            "NoteDatabase"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE as NoteDatabase
        }
    }
}