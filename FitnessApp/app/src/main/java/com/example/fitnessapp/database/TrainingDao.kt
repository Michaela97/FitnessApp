package com.example.fitnessapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fitnessapp.database.data.Exercise
import com.example.fitnessapp.database.data.Training

@Dao
interface TrainingDao {


    //inserting training with training exercises
//    fun insert(training: Training) {
//        var trainingId = insertTraining(training).toInt()
//        training.exercises.forEach {
//            it.trainingId = trainingId
//            insertTrainingExercises(it)
//        }
//    }

    @Insert
    fun insert(training : Training)

//    @Insert
//     fun insertTrainingExercises(trainingExercise : Exercise)

    @Query("select * from training")
    fun findAllTraining() : List<Training>

    @Query("select * from training")
    fun findAllTrainingLiveData() : LiveData<List<Training>>

//    @Query("select * from exercise where trainingId = :trainingId")
//    abstract fun findAllTrainingExercise(trainingId : Int) : List<Exercise>


    @Query("select * from exercise where trainingId = :trainingId")
    fun getExercisesByTrainingID(trainingId : Int) : List<Exercise>

//    fun findAllLiveData() : LiveData<List<Training>> {
//        val trainings = findAllTraining()
//
//        for(training in trainings) {
//            val trainingexercises = findAllTrainingExercise(training.trainingId)
//            training.exercises = trainingexercises.toMutableList()
//        }
//
//        val liveData = MutableLiveData<List<Training>>()
//        liveData.postValue(trainings)
//        return liveData
//    }
//
//    fun findAll() : List<Training> {
//
////        for(training in trainings) {
////            val trainingexercises = findAllTrainingExercise(training.trainingId)
////            training.exercises = trainingexercises.toMutableList()
////        }
////
//        return findAllTraining()
//    }

//    fun deleteAll() {
//        deleteAllTraining()
//
//    }

    @Query("delete from training")
    fun deleteAllTraining()
//    @Query("delete from training_exercise")
//    fun deleteAllTrainingExercise()


}