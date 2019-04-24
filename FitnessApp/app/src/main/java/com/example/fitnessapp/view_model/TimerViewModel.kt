package com.example.fitnessapp.view_model

import android.app.Application
import android.os.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fitnessapp.ExerciseListViewModel.Companion.EXERCISES_KEY
import com.example.fitnessapp.database.data.Exercise
import com.example.fitnessapp.repository.FitnessRepository

class TimerViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var exercises: LiveData<List<Exercise>>
    private lateinit var fitnessRepository: FitnessRepository
    private var trainingID: Int = 0
    //name of exercise
    val name = MutableLiveData<String>()
    //timer
    val time = MutableLiveData<String>()
    // visibility for "start again button" which will be display after training ends
    val visible = MutableLiveData<Boolean>()
    private lateinit var listIterator: ListIterator<Exercise>

    //this wont be hardcoded in the future
    private val countDownTimer = object : CountDownTimer(10000, 1000) {

        override fun onTick(millisUntilFinished: Long) {
            time.value = (millisUntilFinished / 1000).toString()
        }

        override fun onFinish() {
            if (listIterator.hasNext()){

                name.value = listIterator.next().name
                restart()
        } else
                name.value = "Finished. Good Job!"
                visible.value = true

        }
    }

    //passing trainingID to get exercises...
    fun init(bundle: Bundle) {
        fitnessRepository = FitnessRepository.getInstance(this.getApplication())
        trainingID = bundle.getInt(EXERCISES_KEY)
        exercises = fitnessRepository.getAllExercisesByTraining(trainingID)

    }

    fun restart(){
        countDownTimer.start()
    }

    //loading exercises from the list
    fun countDownTimerStart() {
        visible.value = false
        listIterator = exercises.value!!.listIterator(0)
        name.value = listIterator.next().name
        countDownTimer.start()
    }



}
