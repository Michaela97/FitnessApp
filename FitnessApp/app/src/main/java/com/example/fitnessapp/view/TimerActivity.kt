package com.example.fitnessapp.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.fitnessapp.R

class TimerActivity : AppCompatActivity() {

    var timer : Int = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exerice_in_progress)

    }

}
