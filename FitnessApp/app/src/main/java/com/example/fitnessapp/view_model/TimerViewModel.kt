package com.example.fitnessapp.view_model

import android.app.Application
import android.os.Bundle
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fitnessapp.ExerciseListViewModel.Companion.EXERCISES_KEY
import com.example.fitnessapp.database.data.Exercise
import com.example.fitnessapp.repository.FitnessRepository
import kotlinx.android.synthetic.main.timer_fragment.*

class TimerViewModel(application: Application) : AndroidViewModel(application) {

    public lateinit var exercises: LiveData<List<Exercise>>
    private lateinit var fitnessRepository: FitnessRepository
    private var trainingID: Int = 0
    public val name = MutableLiveData<String>()
    public val time = MutableLiveData<String>()
    private lateinit var listIterator: ListIterator<Exercise>

    private val countDownTimer = object : CountDownTimer(10000, 1000) {

        override fun onTick(millisUntilFinished: Long) {
            time.value = (millisUntilFinished / 1000).toString()
        }

        override fun onFinish() {
            if (listIterator.hasNext())
                name.value = listIterator.next().name
            else
                name.value = "Finished"
            restart()
        }
    }

    fun init(bundle: Bundle) {
        fitnessRepository = FitnessRepository.getInstance(this.getApplication())
        trainingID = bundle.getInt(EXERCISES_KEY)
        exercises = fitnessRepository.getAllExercisesByTraining(trainingID)
    }

    fun restart(){
        countDownTimer.start()
    }

    fun countDownTimerStart() {
        listIterator = exercises.value!!.listIterator(1)
        countDownTimer.start()
    }
}
