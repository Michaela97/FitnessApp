package com.example.fitnessapp.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessapp.ExerciseListViewModel
import com.example.fitnessapp.R
import com.example.fitnessapp.view_model.util.ExerciseListAdapter
import kotlinx.android.synthetic.main.exercise_list_fragment.*


class ExerciseListFragment : Fragment() {


    private lateinit var viewModel: ExerciseListViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.exercise_list_fragment, container, false)
    }

    override fun onViewCreated(view : View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ExerciseListViewModel::class.java)

        if (savedInstanceState == null) {
            viewModel.init(arguments!!)
        }

        fab.setOnClickListener{view -> viewModel.startTimer(view)}

        exerciseList.hasFixedSize()
        exerciseList.layoutManager = LinearLayoutManager(exerciseList.context)
        val adapter = ExerciseListAdapter()
        exerciseList.adapter = adapter
        viewModel.getAllExercises().observe(this, Observer {adapter.setExercises(it)})



    }







}


