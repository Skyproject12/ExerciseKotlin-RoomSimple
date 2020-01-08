package com.example.roomapplication.Ui.Main

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapplication.Data.Source.Model.Entity.Note
import com.example.roomapplication.R
import com.example.roomapplication.Ui.Insert.InsertActivity
import com.example.roomapplication.Utils.NoteDiffCallback
import java.util.ArrayList
import com.example.roomapplication.Ui.Main.MainAdapter.Viewholder
import kotlinx.android.synthetic.main.item_note.view.*

// make recyclerview with pagging
public class MainAdapter internal constructor(private val activity: Activity) :
    RecyclerView.Adapter<Viewholder>() {
    private val listNotes = ArrayList<Note>()
    // defination object with setter
    fun setListNote(listNote: List<Note>) {
        val diffCallback = NoteDiffCallback(this.listNotes, listNote)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listNotes.clear()
        this.listNotes.addAll(listNote)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) {
            // set item in recyclerview
            with(itemView) {
                tv_item_title.text = note.title
                tv_item_date.text = note.date
                tv_item_description.text = note.description
                cv_item_note.setOnClickListener {
                    val intent = Intent(activity, InsertActivity::class.java)
                    // send the position use intent
                    intent.putExtra(InsertActivity.EXTRA_POSITION, adapterPosition)
                    intent.putExtra(InsertActivity.EXTRA_NOTE, note)
                    // set the activity with result
                    activity.startActivityForResult(intent, InsertActivity.REQUEST_UPDATE)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder = Viewholder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
    )

    override fun getItemCount(): Int = listNotes.size
    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bind(listNotes.get(position))
    }

}