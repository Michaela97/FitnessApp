package com.example.fitnessapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_exerice_in_progress.*

class ExericeInProgress : AppCompatActivity() {

    var timer : Int = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exerice_in_progress)

    }

}
