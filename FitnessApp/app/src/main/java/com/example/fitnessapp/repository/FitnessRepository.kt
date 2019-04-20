package com.example.fitnessapp.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.fitnessapp.database.data.Exercise
import com.example.fitnessapp.database.FitnessDatabase
import com.example.fitnessapp.database.data.FitnessDao
import com.example.fitnessapp.database.data.Training

class FitnessRepository(application: Application) {

    private var fitnessDao: FitnessDao
    private var trainings: LiveData<List<Training>>
    private var exerciseMediator = MediatorLiveData<List<Exercise>>()

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

    fun insertTrainingWithExercises(training: Training, exercises: List<Exercise>) {
        InsertAsyncTask(fitnessDao).execute(TrainingWithExercises(training, exercises))
    }


    private class InsertAsyncTask(val fitnessDao: FitnessDao) : AsyncTask<TrainingWithExercises, Unit, Unit>() {
        override fun doInBackground(vararg trainingWithExercises: TrainingWithExercises?) {
            val (training, exercises) = trainingWithExercises[0]!!
            fitnessDao.insertTrainingWithExercises(training, exercises)
        }
    }

}

data class TrainingWithExercises(val training: Training, val exercises: List<Exercise>)