package com.example.fitnessapp.view



import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.fitnessapp.R
import com.example.fitnessapp.view_model.TrainingListViewModel
import com.example.fitnessapp.view_model.util.TrainingListAdapter
import kotlinx.android.synthetic.main.training_list_fragment.*


class TrainingListFragment : Fragment() {

    private lateinit var viewModel: TrainingListViewModel
    companion object {
        const val EXERCISES = "EXERCISES"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.training_list_fragment, container, false)
    }

    override fun onViewCreated(view : View ,savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TrainingListViewModel::class.java)

        if (savedInstanceState == null) {
            viewModel.init()
        }

        //trainingList.hasFizedSize()
        trainingList.layoutManager = LinearLayoutManager(trainingList.context)
        val adapter = TrainingListAdapter(viewModel)
        trainingList.adapter = adapter

        viewModel.getAllTrainings().observe(this, Observer { adapter.setTrainings(it) })


    }





}


