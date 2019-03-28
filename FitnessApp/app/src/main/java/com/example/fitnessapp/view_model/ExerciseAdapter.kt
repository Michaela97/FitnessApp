package com.example.fitnessapp.view_model
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.fitnessapp.model.ExerciseType
import com.example.fitnessapp.R

class ExerciseAdapter(private val items: List<ExerciseType>) : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view  = inflater.inflate(R.layout.exercises_list, parent, false)
        return ViewHolder(view) {
            Toast.makeText(parent.context, "Clicked ${items[it].name}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.name.text = items[position].name
        viewHolder.time.text = items[position].time
    }


    class ViewHolder(itemView: View, private val listener: (Int) -> Unit) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
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
