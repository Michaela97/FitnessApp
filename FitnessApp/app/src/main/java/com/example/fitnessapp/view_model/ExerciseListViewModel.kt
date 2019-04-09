package com.example.fitnessapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.fitnessapp.database.data.Exercise
import com.example.fitnessapp.repository.ExerciseRepository
import com.example.fitnessapp.view_model.util.ListAdapter

class ExerciseListViewModel(application : Application) : AndroidViewModel(application) {

    private lateinit var db : ExerciseRepository
    private lateinit var exercises : LiveData<List<Exercise>>
    private lateinit var adapter : ListAdapter

    fun init() {
        db = ExerciseRepository(this.getApplication())
        exercises = db.getAllExercises()

    }

    fun insert(exercise: Exercise) {
        db.insert(exercise)
    }

    fun getAllExercises() : LiveData<List<Exercise>> = exercises
}
