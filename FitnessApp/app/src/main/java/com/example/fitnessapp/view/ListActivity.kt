package com.example.fitnessapp.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*
import com.example.fitnessapp.model.ExerciseType
import com.example.fitnessapp.R


class ListActivity : AppCompatActivity() {

    private val exerciseTypes : ArrayList<ExerciseType> = ArrayList()
    private lateinit var mDbWorkerThread : DbWorkerThread
    private val mUiHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()



//        addExercises()
//
//        exercise_list.hasFixedSize()
//        exercise_list.layoutManager = LinearLayoutManager(this)
//
//        val exerciseAdapter = ExerciseAdapter(exerciseTypes)
//        exercise_list.adapter = exerciseAdapter

        //button
        fab.setOnClickListener{
            startActivity(Intent(this, TimerActivity::class.java))
        }

    }

    private fun addExercises() {
        exerciseTypes.add(ExerciseType("Plank", "1 min"))
        exerciseTypes.add(ExerciseType("Squats", "60x"))
        exerciseTypes.add(ExerciseType("Push up", "12x"))
        exerciseTypes.add(ExerciseType("Jumps", "5 min"))
        exerciseTypes.add(ExerciseType("Plank", "1 min"))
        exerciseTypes.add(ExerciseType("Squats", "60x"))
        exerciseTypes.add(ExerciseType("Push up", "12x"))
        exerciseTypes.add(ExerciseType("Jumps", "5 min"))
        exerciseTypes.add(ExerciseType("Plank", "1 min"))
        exerciseTypes.add(ExerciseType("Squats", "60x"))
        exerciseTypes.add(ExerciseType("Push up", "12x"))
        exerciseTypes.add(ExerciseType("Jumps", "5 min"))
        exerciseTypes.add(ExerciseType("Plank", "1 min"))
        exerciseTypes.add(ExerciseType("Squats", "60x"))
        exerciseTypes.add(ExerciseType("Push up", "12x"))
        exerciseTypes.add(ExerciseType("Jumps", "5 min"))

    }



}
