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

        //floating buttons
        fab.setOnClickListener{view -> viewModel.startTimer(view)}
        fab_add_exercise.setOnClickListener{ showAddExerciseDialog()}

        //init recycle view
        exerciseList.hasFixedSize()
        exerciseList.layoutManager = LinearLayoutManager(exerciseList.context)
        val adapter = ExerciseListAdapter(viewModel)
        exerciseList.adapter = adapter
        viewModel.getAllExercises().observe(this, Observer {adapter.setExercises(it)})

    }

    //alert dialog with input
    private fun showAddExerciseDialog() {
        val builder = AlertDialog.Builder(context!!)
        builder.setTitle("Add exercise")

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
                viewModel.addExercise(exerciseEditText.text.toString())
                dialog.dismiss()
            }
        }

        builder.setNegativeButton(android.R.string.cancel) { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

}


