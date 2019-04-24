package com.example.fitnessapp.view_model.util
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.database.data.Training
import com.example.fitnessapp.view_model.TrainingListViewModel

class TrainingListAdapter(val viewModel : TrainingListViewModel) : RecyclerView.Adapter<TrainingListAdapter.ViewHolder>() {

    private var trainings : List<Training>? = null
    companion object {
        const val TAG = "TrainingListAdapter"
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view  = inflater.inflate(R.layout.trainings_list_item, parent, false)
        return this.ViewHolder(view)
    }
    fun setTrainings(trainings: List<Training>) {
        this.trainings = trainings
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (trainings!= null) {
           holder.title.text = trainings!![position].title
        } else {
            Log.d(TAG, "TODO")
        }
    }

    override fun getItemCount(): Int {
        if (trainings != null)
            return trainings!!.size
        return 0
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var title : TextView = itemView.findViewById(R.id.trainingName)

        init {
            itemView.setOnClickListener(this)

        }

        override fun onClick(v: View) {
            viewModel.openExerciseList(adapterPosition, v)

        }



    }

    //it will be shown before deleting
    fun areYouSureDialog(view : View, position : Int) {
        val builder = AlertDialog.Builder(view.context)

        // Set the alert dialog title
        builder.setTitle("Are you sure you want to delete this training?")
        // Display a message on alert dialog
        builder.setMessage("After pressing YES button, training will be deleted from the database")

        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("YES") { dialog, which ->
            viewModel.deleteTraining(trainings!![position])

        }
        builder.setNegativeButton("No"){dialog,which ->
            dialog.cancel()
        }

        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }

}