package com.example.fitnessapp

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.fitnessapp.database.data.Exercise
import com.example.fitnessapp.repository.FitnessRepository
import com.example.fitnessapp.view_model.TrainingListViewModel.Companion.TRAININGID_KEY

class ExerciseListViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var exercises: LiveData<List<Exercise>>
    private lateinit var fitnessRepository: FitnessRepository
    private var trainingID : Int = 0
    companion object {
        const val TAG = "ExerciseListViewModel"
    }

    fun init(bundle: Bundle) {
        fitnessRepository = FitnessRepository.getInstance(this.getApplication())
        trainingID = bundle.getInt(TRAININGID_KEY)
        exercises = fitnessRepository.getAllExercisesByTraining(trainingID)
    }

    fun getAllExercises(): LiveData<List<Exercise>> = exercises


}
