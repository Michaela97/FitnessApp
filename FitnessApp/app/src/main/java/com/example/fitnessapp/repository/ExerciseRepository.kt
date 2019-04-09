package com.example.fitnessapp.repository

import android.app.Application
import android.os.AsyncTask

import androidx.lifecycle.LiveData
import com.example.fitnessapp.database.data.Exercise
import com.example.fitnessapp.database.data.ExerciseDao
import com.example.fitnessapp.database.data.FitnessDatabase

class ExerciseRepository(application: Application) {

    private var exercisesDao: ExerciseDao
    private var exercises: LiveData<List<Exercise>>

    companion object {
        private var INSTANCE: ExerciseRepository? = null

        fun getInstance(application: Application): ExerciseRepository {
            if (INSTANCE == null) {
                synchronized(ExerciseRepository::class) {
                    INSTANCE = ExerciseRepository(application)
                }
            }
            return INSTANCE!!
        }
    }

    init {
        val db = FitnessDatabase.getInstance(application)
        exercisesDao = db!!.exerciseDao()
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