package com.example.fitnessapp.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.fitnessapp.ExerciseListViewModel
import com.example.fitnessapp.R
import kotlinx.android.synthetic.main.exercise_list_fragment.*


class ExerciseListFragment : Fragment() {


    private lateinit var viewModel: ExerciseListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.exercise_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ExerciseListViewModel::class.java)

        if (savedInstanceState == null) {
            viewModel.init()
        }

        exerciseList.hasFixedSize()
    }



}
