package com.example.fitnessapp.view_model

import android.app.Application
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.lifecycle.AndroidViewModel

import androidx.lifecycle.LiveData
import androidx.navigation.Navigation
import com.example.fitnessapp.R
import com.example.fitnessapp.database.data.Training
import com.example.fitnessapp.repository.FitnessRepository

class TrainingListViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var trainigs: LiveData<List<Training>>
    private lateinit var fitnessRepository: FitnessRepository

    companion object {
        const val TRAININGID_KEY = "trainingID"
    }


    fun init() {
        fitnessRepository = FitnessRepository.getInstance(this.getApplication())
        trainigs = fitnessRepository.getAllTrainings()
    }

    fun getAllTrainings(): LiveData<List<Training>> = trainigs

    //opening new fragment with trainingID to get exercises
    fun openExerciseList(position: Int, view: View) {
        val training = trainigs.value!!.get(position)
        val bundle = Bundle()
        bundle.putInt(TRAININGID_KEY, training.trainingId)
        Navigation.findNavController(view).navigate(R.id.exerciseListFragment, bundle)
    }

    fun addTraining(training: String) {
        var training = Training(training)
        fitnessRepository.insertTraining(training)
    }

    fun deleteTraining(training: Training) {
        fitnessRepository.deleteTraining(training)
    }

}
