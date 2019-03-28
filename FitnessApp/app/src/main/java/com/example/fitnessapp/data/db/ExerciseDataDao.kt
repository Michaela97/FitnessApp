package com.example.fitnessapp.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

//data access object
@Dao
interface ExerciseDataDao {

    @Query("SELECT * from exercise_type")
    fun getALl() : List<Exercise>

    @Insert(onConflict = REPLACE)
    fun insert(exercise: Array<out Exercise?>)

    @Query("DELETE from exercise_type")
    fun deleteAll()
}