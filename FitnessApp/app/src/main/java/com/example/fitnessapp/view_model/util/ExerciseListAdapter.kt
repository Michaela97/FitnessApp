package com.example.fitnessapp.view_model.util
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.database.data.Exercise

class ExerciseListAdapter : RecyclerView.Adapter<ExerciseListAdapter.ViewHolder>() {

    private var exercises : List<Exercise>? = null
    companion object {
        const val TAG = "ExerciseListAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view  = inflater.inflate(R.layout.exercises_list_item, parent, false)
        return ViewHolder(view) {
            Toast.makeText(parent.context, "Clicked ${exercises!![it].name}", Toast.LENGTH_SHORT).show()
        }
    }
    fun setExercises(exercises: List<Exercise>) {
        this.exercises = exercises
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        if (exercises != null)
            return exercises!!.size
        return 0
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if (exercises!=null) {
            viewHolder.name.text = exercises!![position].name
            viewHolder.time.text = exercises!![position].minutes.toString()

        } else {
            Log.d(TAG, "TODO")
        }
    }


    class ViewHolder(itemView: View, private val listener: (Int) -> Unit) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var name : TextView = itemView.findViewById(R.id.exerciseName)
        var time : TextView = itemView.findViewById(R.id.exerciseTime)


        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view : View?) {
            listener.invoke(adapterPosition)

        }
    }

}
