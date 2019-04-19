package com.example.fitnessapp.view_model

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.lifecycle.AndroidViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.example.fitnessapp.R
import com.example.fitnessapp.database.data.Training
import com.example.fitnessapp.database.data.TrainingExercise
import com.example.fitnessapp.repository.TrainingRepository

class TrainingListViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var trainigs: LiveData<List<Training>>
    private lateinit var trainingRepository: TrainingRepository

    companion object{
        public const val TRAININGID_KEY = "trainingID"
    }


    fun init() {
        trainingRepository= TrainingRepository.getInstance(this.getApplication())
        trainigs = trainingRepository.getAllTrainings()
    }

    fun getAllTrainings(): LiveData<List<Training>> = trainigs



    fun openExerciseList(position : Int, view: View) {
        val training = trainigs.value!!.get(position)
        val bundle = Bundle()
        bundle.putInt(TRAININGID_KEY,training.trainingId)
        Navigation.findNavController(view).navigate(R.id.exerciseListFragment, bundle)
    }


}
