package com.example.fitnessapp.view



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

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

        trainingList.layoutManager = LinearLayoutManager(trainingList.context)
        val adapter = TrainingListAdapter(viewModel)
        trainingList.adapter = adapter

        viewModel.getAllTrainings().observe(this, Observer { adapter.setTrainings(it) })

        fab_add_training.setOnClickListener{view -> showAddTrainingDialog()}

    }

    //alert dialog with input

    private fun showAddTrainingDialog() {
        val builder = AlertDialog.Builder(context!!)
        builder.setTitle("Create training")

        val view = layoutInflater.inflate(R.layout.add_dialog, null)

        val exerciseEditText = view.findViewById(R.id.categoryEditText) as EditText

        builder.setView(view)

        // set up the ok button
        builder.setPositiveButton(android.R.string.ok) { dialog, _ ->
            val newExercise = exerciseEditText.text
            var isValid = true
            if (newExercise.isBlank()) {
                exerciseEditText.error = getString(R.string.validation_empty)
                isValid = false
            }

            if (isValid) {
                viewModel.addTraining(exerciseEditText.text.toString())
                dialog.dismiss()
            }
        }

        builder.setNegativeButton(android.R.string.cancel) { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }
}


