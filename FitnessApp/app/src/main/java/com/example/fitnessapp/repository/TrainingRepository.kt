package com.example.fitnessapp.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.fitnessapp.database.TrainingDao
import com.example.fitnessapp.database.data.Exercise
import com.example.fitnessapp.database.data.FitnessDatabase
import com.example.fitnessapp.database.data.Training

class TrainingRepository(application: Application) {

    private var trainingDao: TrainingDao
    private var trainings: MutableLiveData<List<Training>> = MutableLiveData()
    private var exerciseMediator = MediatorLiveData<List<Exercise>>()

    companion object {
        private var INSTANCE: TrainingRepository? = null

        fun getInstance(application: Application): TrainingRepository {
            if (INSTANCE == null) {
                synchronized(TrainingRepository::class) {
                    INSTANCE = TrainingRepository(application)
                }
            }
            return INSTANCE!!
        }
    }

    init {
        val db = FitnessDatabase.getInstance(application)
        trainingDao = db!!.trainingDao()
        FindAllAsyncTask(trainingDao).execute(trainings)
    }

    fun getAllTrainings() = trainings

    fun getAllExercisesByTraining(trainingID: Int): LiveData<List<Exercise>> {

        val liveData = MutableLiveData<List<Exercise>>()
        FindAllByTrainingAsyncTask(trainingDao, trainingID).execute(liveData)
        return liveData

    }

    fun insert(training: Training) {
        InsertAsyncTask(trainingDao).execute(training)
    }


    private class InsertAsyncTask(val trainingDao: TrainingDao) : AsyncTask<Training, Unit, Unit>() {
        override fun doInBackground(vararg training: Training?) {
            trainingDao.insert(training[0]!!)
        }
    }

    private class FindAllAsyncTask(val trainingDao: TrainingDao) :
        AsyncTask<MutableLiveData<List<Training>>, Unit, Unit>() {

        override fun doInBackground(vararg training: MutableLiveData<List<Training>>) {
            training[0].postValue(trainingDao.findAllTraining())
        }
    }

    private class FindAllByTrainingAsyncTask(val trainingDao: TrainingDao, val trainingID: Int) :
        AsyncTask<MutableLiveData<List<Exercise>>, Unit, Unit>() {

        override fun doInBackground(vararg training: MutableLiveData<List<Exercise>>) {
            val list = trainingDao.getExercisesByTrainingID(trainingID)
            training[0].postValue(list)
        }
    }

}