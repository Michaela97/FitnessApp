package com.example.fitnessapp.database.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

//data access object
@Dao
abstract class FitnessDao {

    @Insert(onConflict = REPLACE)
    abstract fun insertExercise(exercise: Exercise)

    @Insert(onConflict = REPLACE)
    abstract fun insertTraining(training: Training) : Long

    fun insertTrainingWithExercises(training: Training, exercises : List<Exercise>) {
        val trainingId = insertTraining(training).toInt()
        exercises.forEach {
            it.trainingId = trainingId
            insertExercise(it)
        }
    }

    @Delete
    abstract fun deleteExercise(exercise: Exercise)

    @Delete
    abstract fun deleteTraining(training: Training)

    @Query("delete from exercise")
    abstract fun deleteAllExercises()

    @Query("delete from training")
    abstract fun deleteAllTraining()

    fun deleteAll() {
        deleteAllExercises()
        deleteAllTraining()
    }

    @Query("select * from training")
    abstract fun getAllTrainings() : LiveData<List<Training>>

    @Query("select * from exercise where trainingId like :trainingId")
    abstract fun getAllExercisesByTrainingId(trainingId : Int) : LiveData<List<Exercise>>








}