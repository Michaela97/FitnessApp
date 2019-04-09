package com.example.fitnessapp.repository

import android.app.Application
import android.os.AsyncTask

import androidx.lifecycle.LiveData
import com.example.fitnessapp.database.data.Exercise
import com.example.fitnessapp.database.data.ExerciseDao
import com.example.fitnessapp.database.data.ExerciseDatabase

class ExerciseRepository(application: Application) {

    private var exercisesDao: ExerciseDao
    private var exercises: LiveData<List<Exercise>>


    init {
        val db = ExerciseDatabase.getInstance(application)
        exercisesDao = db!!.exerciseDataDao()
        exercises = exercisesDao.findAll()
    }

    fun getAllExercises() = exercises

    fun insert(exercise: Exercise) {
        InsertAsyncTask(exercisesDao).execute(exercise)
    }
    
    private class InsertAsyncTask(val exerciseDao: ExerciseDao) : AsyncTask<Exercise, Unit, Unit>() {
        override fun doInBackground(vararg exercise: Exercise?) {
            exerciseDao.insert(exercise[0]!!)
        }
    }
}