package com.example.roomapplication.Utils

import androidx.recyclerview.widget.DiffUtil
import com.example.roomapplication.Data.Source.Model.Entity.Note

// make component for pagging recyclerview
class NoteDiffCallback(private val mOldNoteList: List<Note>, private val mNewNoteList: List<Note>) :
    DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldNoteList[oldItemPosition]
        val newEmployee = mNewNoteList[newItemPosition]
        return oldEmployee.title == newEmployee.title && oldEmployee.title == newEmployee.description
    }

    override fun getOldListSize(): Int {
        return mOldNoteList.size
    }

    override fun getNewListSize(): Int {
        return mNewNoteList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldNoteList[oldItemPosition].id == mNewNoteList[newItemPosition].id
    }

    override fun getChangePayload(oldItemPosition: Int, newItemposition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemposition)
    }

}