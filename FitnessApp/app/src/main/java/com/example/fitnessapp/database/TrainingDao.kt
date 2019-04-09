package com.example.fitnessapp.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fitnessapp.database.data.Training
import com.example.fitnessapp.database.data.TrainingExercise

@Dao
abstract class TrainingDao {

    //inserting training with training exercises
    fun insert(training: Training) {
        var trainingId = insertTraining(training).toInt()
        training.exercises.forEach {
            it.trainingId = trainingId
            insertTrainingExercises(it)
        }
    }


    @Insert
    abstract fun insertTraining(training : Training) : Long

    @Insert
    abstract fun insertTrainingExercises(trainingExercise : TrainingExercise)

    @Query("select * from training")
    abstract fun findAllTraining() : List<Training>

    @Query("select * from training_exercise where trainingId = :trainingId")
    abstract fun findAllTrainingExercise(trainingId : Int) : List<TrainingExercise>

    fun findAll() : LiveData<List<Training>> {
        val trainings = findAllTraining()

        for(training in trainings) {
            val trainingexercises = findAllTrainingExercise(training.trainingId)
            training.exercises = trainingexercises.toMutableList()
        }

        val liveData = MutableLiveData<List<Training>>()
        liveData.postValue(trainings)
        return liveData
    }

    fun deleteAll() {
        deleteAllTraining()
        deleteAllTrainingExercise()
    }

    @Query("delete from training")
    abstract fun deleteAllTraining()
    @Query("delete from training_exercise")
    abstract fun deleteAllTrainingExercise()


}