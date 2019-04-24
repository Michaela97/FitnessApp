package com.example.fitnessapp.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.fitnessapp.database.data.Exercise
import com.example.fitnessapp.database.FitnessDatabase
import com.example.fitnessapp.database.data.FitnessDao
import com.example.fitnessapp.database.data.Training

class FitnessRepository(application: Application) {

    private var fitnessDao: FitnessDao
    private var trainings: LiveData<List<Training>>

    companion object {
        private var INSTANCE: FitnessRepository? = null

        fun getInstance(application: Application): FitnessRepository {
            if (INSTANCE == null) {
                synchronized(FitnessRepository::class) {
                    INSTANCE = FitnessRepository(application)
                }
            }
            return INSTANCE!!
        }
    }

    init {
        val db = FitnessDatabase.getInstance(application)
        fitnessDao = db!!.fitnessDao()
        trainings = fitnessDao.getAllTrainings()
    }

    fun getAllTrainings() = trainings

    fun getAllExercisesByTraining(trainingID: Int): LiveData<List<Exercise>> {
        return fitnessDao.getAllExercisesByTrainingId(trainingID)
    }

    fun insertTraining(training: Training) {
        InsertTrainingAsyncTask(fitnessDao).execute(training)
    }

    fun insertExercise(exercise: Exercise) {
        InsertExerciseAsyncTask(fitnessDao).execute(exercise)
    }

    fun deleteTraining(training: Training) {
        DeleteTrainingAsyncTask(fitnessDao).execute(training)
    }

    fun deleteExercise(exercise: Exercise) {
        DeleteExerciseAsyncTask(fitnessDao).execute(exercise)
    }

    private class InsertTrainingAsyncTask(val fitnessDao: FitnessDao) : AsyncTask<Training, Unit, Unit>() {
        override fun doInBackground(vararg training: Training?) {
            val training = training[0]!!
            fitnessDao.insertTraining(training)
        }
    }

    private class InsertExerciseAsyncTask(val fitnessDao: FitnessDao) : AsyncTask<Exercise, Unit, Unit>() {
        override fun doInBackground(vararg exercises: Exercise?) {
            val exercise = exercises[0]!!
            fitnessDao.insertExercise(exercise)
        }
    }

    private class DeleteExerciseAsyncTask(val fitnessDao: FitnessDao) : AsyncTask<Exercise, Unit, Unit>() {
        override fun doInBackground(vararg exercises: Exercise?) {
            val exercise = exercises[0]!!
            fitnessDao.deleteExercise(exercise)
        }
    }

    private class DeleteTrainingAsyncTask(val fitnessDao: FitnessDao) : AsyncTask<Training, Unit, Unit>() {
        override fun doInBackground(vararg training: Training?) {
            val training = training[0]!!
            fitnessDao.deleteTraining(training)
        }
    }
}
