package com.example.roomapplication.Ui.Insert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.roomapplication.Data.Source.Model.Entity.Note
import com.example.roomapplication.R
import com.example.roomapplication.Utils.DateHelper
import com.example.roomapplication.ViewModel.Insert.InsertViewModel
import com.example.roomapplication.ViewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_insert.*

class InsertActivity : AppCompatActivity() {

    // defination final or utils of variable
    companion object {
        const val EXTRA_NOTE = "extra_note"
        const val EXTRA_POSITION = "extra_position"
        const val REQUEST_ADD = 100
        const val RESULT_ADD = 101
        const val REQUEST_UPDATE = 200
        const val RESULT_UPDATE = 201
        const val RESULT_DELETE = 301
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }

    private var isEdit = false
    private var note: Note? = null
    private var position = 0
    private lateinit var insertViewModel: InsertViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        // initial viewmodel
        insertViewModel = obtainViewModel(this@InsertActivity)

        // mengecek hasil intent ketika fab di klik
        note = intent.getParcelableExtra(EXTRA_NOTE)
        if (note != null) {
            position = intent.getIntExtra(EXTRA_POSITION, 0)
            isEdit = true
        } else {
            note = Note()
        }
        val actionBarTitle: String
        val btnTitle: String
        // ketika edit sama dengan true
        if (isEdit) {
            actionBarTitle = "Ubah"
            btnTitle = "Update"
            // ketika note tidak null
            if (note != null) {
                // setetxt the edittext
                note?.let { note ->
                    edt_title.setText(note.title)
                    edt_description.setText(note.description)
                }
            }
        } else {
            // ketika insert sama dengan true
            actionBarTitle = "Tambah"
            btnTitle = "Simpan"
        }

        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        btn_submit.text = btnTitle
        // ketika btn submit di klik
        btn_submit.setOnClickListener {
            val title = edt_title.text.toString().trim()
            val description = edt_description.text.toString().trim()
            if (title.isEmpty()) {
                edt_title.error = "empty data"
            } else if (description.isEmpty()) {
                edt_description.error = "empty data"
            } else {
                note.let { note ->
                    // set object title berdasarkan title
                    note?.title = title
                    note?.description = description
                }
                // intent put Extra
                val intent = Intent()
                // put extra note
                intent.putExtra(EXTRA_NOTE, note)
                // put extra position
                intent.putExtra(EXTRA_POSITION, position)
                if (isEdit) {
                    insertViewModel.update(note as Note)
                    // set resutlt
                    setResult(RESULT_UPDATE, intent)
                    // ketika di finish
                    finish()
                } else {
                    note.let { note ->
                        note?.date = DateHelper.getCurrentDate()
                    }
                    // insert note with object Note
                    insertViewModel.insert(note as Note)
                    // ketika berhasil add maka set result intent add
                    setResult(RESULT_ADD, intent)
                    finish()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // ketika edit maka tampilkan menu
        if (isEdit) {
            menuInflater.inflate(R.menu.menu_form, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> showAlertDialog(ALERT_DIALOG_DELETE)
            // ketika melakukan back maka akan muncul dialog
            android.R.id.home -> showAlertDialog(ALERT_DIALOG_CLOSE)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        // ketika melakukan backpress maka akan muncul dialog
        showAlertDialog(ALERT_DIALOG_CLOSE)
    }

    private fun showAlertDialog(type: Int) {
        // ketika status close
        val isDialogClose = type == ALERT_DIALOG_CLOSE
        // defination title alertdialog
        val dialogTitle: String
        // defination messsge alertdialog
        val dialogMessage: String
        // jika status close
        if (isDialogClose) {
            //set dialog title for alert dialog
            dialogTitle = getString(R.string.cancel)
            dialogMessage = getString(R.string.message_cancel)
        } else {
            dialogMessage = getString(R.string.message_delete)
            dialogTitle = getString(R.string.delete)
        }
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(dialogTitle)
        alertDialogBuilder
            .setMessage(dialogMessage)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.yes)) { dialog, id ->
                // ketika status dialog close
                if (isDialogClose) {
                    finish()
                } else {
                    // delete data
                    insertViewModel.delete(note as Note)
                    val intent = Intent()
                    // send the positoin of data
                    intent.putExtra(EXTRA_POSITION, position)
                    // set status result for know fungsion yang di jalanakan
                    setResult(RESULT_DELETE, intent)
                    finish()
                }
            }
            .setNegativeButton(getString(R.string.no)) { dialog, id ->
                dialog.cancel()
            }
        // show the alertdialog
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    // defination of viewmodel
    private fun obtainViewModel(activity: AppCompatActivity): InsertViewModel {
        // defination viewmodel factory
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(InsertViewModel::class.java)
    }
}
