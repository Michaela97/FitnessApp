package com.example.fitnessapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fitnessapp.database.data.Exercise
import com.example.fitnessapp.repository.ExerciseRepository
import com.example.fitnessapp.view_model.util.ListAdapter

class ExerciseListViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var exercises: LiveData<List<Exercise>>
    private lateinit var exerciseRepository: ExerciseRepository

    fun init() {

        exerciseRepository = ExerciseRepository.getInstance(this.getApplication())
        exercises = exerciseRepository.getAllExercises()

    }

    fun getAllExercises(): LiveData<List<Exercise>> = exercises
}
