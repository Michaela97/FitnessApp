package com.example.fitnessapp

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation
import com.example.fitnessapp.database.data.Exercise
import com.example.fitnessapp.repository.FitnessRepository
import com.example.fitnessapp.view_model.TrainingListViewModel.Companion.TRAININGID_KEY

class ExerciseListViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var exercises: LiveData<List<Exercise>>
    private lateinit var fitnessRepository: FitnessRepository
    private var trainingID : Int = 0
    companion object {
        public const val EXERCISES_KEY = "exercises"
    }

    fun init(bundle: Bundle) {
        fitnessRepository = FitnessRepository.getInstance(this.getApplication())
        trainingID = bundle.getInt(TRAININGID_KEY)
        exercises = fitnessRepository.getAllExercisesByTraining(trainingID)
    }

    fun getAllExercises(): LiveData<List<Exercise>> = exercises

    fun startTimer(view: View) {
        val bundle = Bundle()
        bundle.putInt(EXERCISES_KEY, trainingID)
        Navigation.findNavController(view).navigate(R.id.timerFragment, bundle)
    }

    fun addExercise(exercise: String) {
        var exercise = Exercise(exercise, trainingID)
        fitnessRepository.insertExercise(exercise)
        Log.d("ELVM", exercise.toString() + "training ID : " + trainingID.toString())
    }

    fun deleteExercise(exercise: Exercise) {
        fitnessRepository.deleteExercise(exercise)
    }

}
