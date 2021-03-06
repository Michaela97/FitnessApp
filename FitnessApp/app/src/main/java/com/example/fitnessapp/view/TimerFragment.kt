package com.example.fitnessapp.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.fitnessapp.R
import com.example.fitnessapp.view_model.TimerViewModel
import kotlinx.android.synthetic.main.timer_fragment.*

class TimerFragment : Fragment() {

    private lateinit var viewModel: TimerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.timer_fragment, container, false)
    }

    override fun onViewCreated(view : View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TimerViewModel::class.java)

        if (savedInstanceState == null) {
            viewModel.init(arguments!!)
        }

        //start again training button is set to invisible, after training ends it will be visible
        startAgain_button.visibility = View.INVISIBLE

        viewModel.exercises.observe(this, Observer { viewModel.countDownTimerStart() })
        viewModel.name.observe(this, Observer { exercise.text = it })
        viewModel.time.observe(this, Observer { timer.text = it })
        viewModel.visible.observe(this, Observer {
            if (viewModel.visible.value == true)
                startAgain_button.visibility = View.VISIBLE
            else
                startAgain_button.visibility = View.INVISIBLE
        })

        startAgain_button.setOnClickListener { viewModel.countDownTimerStart() }
    }

}
