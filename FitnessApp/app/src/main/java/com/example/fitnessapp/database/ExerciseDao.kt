package com.example.fitnessapp.database.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

//data access object
@Dao
interface ExerciseDao {

    @Query("SELECT * from exercise")
    fun getALl() : List<Exercise>

    @Insert(onConflict = REPLACE)
    fun insert(exercise: Exercise)

    @Query("DELETE from exercise")
    fun deleteAll()

    @Update
    fun update(exercise: Exercise)

    @Delete
    fun delete(exercise: Exercise)

    @Query("select * from exercise")
    fun findAll() : LiveData<List<Exercise>>
}