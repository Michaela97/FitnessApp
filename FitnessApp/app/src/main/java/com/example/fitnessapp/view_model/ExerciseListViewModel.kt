package com.example.fitnessapp

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.fitnessapp.database.data.Exercise
import com.example.fitnessapp.repository.ExerciseRepository
import com.example.fitnessapp.repository.TrainingRepository
import com.example.fitnessapp.view_model.TrainingListViewModel.Companion.TRAININGID_KEY

class ExerciseListViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var exercises: LiveData<List<Exercise>>
    private lateinit var exerciseRepository: ExerciseRepository
    private lateinit var trainingRepository: TrainingRepository
    private var trainingID : Int = 0
    companion object {
        const val TAG = "ExerciseListViewModel"
    }

    fun init(bundle: Bundle) {
        trainingRepository = TrainingRepository.getInstance(this.getApplication())
        exerciseRepository = ExerciseRepository.getInstance(this.getApplication())
        exercises = exerciseRepository.getAllExercises()

        trainingID = bundle.getInt(TRAININGID_KEY)
    }

    fun getAllExercises(): LiveData<List<Exercise>> = trainingRepository.getAllExercisesByTraining(trainingID)


}
