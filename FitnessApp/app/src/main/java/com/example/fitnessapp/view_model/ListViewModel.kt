package com.example.fitnessapp.view_model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.os.AsyncTask
import com.example.fitnessapp.data.db.Exercise
import com.example.fitnessapp.data.db.ExerciseDataDao
import com.example.fitnessapp.data.db.ExerciseDatabase

class ListViewModel(application: Application) : AndroidViewModel(application) {


    private var instance : ExerciseDatabase? = ExerciseDatabase.getInstance(application)
    private lateinit var exerciseDao : ExerciseDataDao

    fun insert(exercise : Exercise) {
        InsertAsyncTask(exerciseDao).execute(exercise);
    }

    class InsertAsyncTask(val mExericseDao : ExerciseDataDao) : AsyncTask<Exercise, Void, Void>() {


        override fun doInBackground(vararg params: Exercise?) : Void? {
            mExericseDao.insert(params)
            return null
        }
    }


}