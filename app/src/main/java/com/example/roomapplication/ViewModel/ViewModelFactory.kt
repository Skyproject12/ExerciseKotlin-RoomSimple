package com.example.roomapplication.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomapplication.ViewModel.Insert.InsertViewModel
import com.example.roomapplication.ViewModel.Main.MainViewModel
import java.lang.IllegalArgumentException

public class ViewModelFactory(private val mApplication: Application) :
    ViewModelProvider.NewInstanceFactory() {
    // defination constructor
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        // this will call in main
        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = ViewModelFactory(application)
                    }
                }
            }
            return INSTANCE as ViewModelFactory
        }

    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        // defination viewmodel
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(mApplication) as T
        } else if (modelClass.isAssignableFrom(InsertViewModel::class.java)) {
            return InsertViewModel(mApplication) as T
        }
        // if the viewmodel unknown class name
        throw IllegalArgumentException("Unknown ViewModel class : ${modelClass.name}")
    }
}