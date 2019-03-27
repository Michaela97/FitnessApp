package com.example.fitnessapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.DividerItemDecoration
import android.view.View
import java.security.AccessController.getContext


class MainActivity : AppCompatActivity() {

    private val exercises : ArrayList<Exercise> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addExercises()

        exercise_list.hasFixedSize()
        exercise_list.layoutManager = LinearLayoutManager(this)

        val exerciseAdapter = ExerciseAdapter(exercises)
        exercise_list.adapter = exerciseAdapter


        fab.setOnClickListener{
            startActivity(Intent(this,ExericeInProgress::class.java))
        }

    }

    private fun addExercises() {
        exercises.add(Exercise("Plank","1 min"))
        exercises.add(Exercise("Squats","60x"))
        exercises.add(Exercise("Push up","12x"))
        exercises.add(Exercise("Jumps","5 min"))
        exercises.add(Exercise("Plank","1 min"))
        exercises.add(Exercise("Squats","60x"))
        exercises.add(Exercise("Push up","12x"))
        exercises.add(Exercise("Jumps","5 min"))
        exercises.add(Exercise("Plank","1 min"))
        exercises.add(Exercise("Squats","60x"))
        exercises.add(Exercise("Push up","12x"))
        exercises.add(Exercise("Jumps","5 min"))
        exercises.add(Exercise("Plank","1 min"))
        exercises.add(Exercise("Squats","60x"))
        exercises.add(Exercise("Push up","12x"))
        exercises.add(Exercise("Jumps","5 min"))

    }



}
