package com.example.fitnessapp.database.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "training")
data class Training(
    var title: String,
    @PrimaryKey(autoGenerate = true)
    var trainingId: Int = 0
) {


    var duration: Int = 0
    @Ignore
    var exercises: MutableList<TrainingExercise> = mutableListOf()


    fun addExercises(exercises: List<Exercise>) {
        exercises.forEach {
            this.exercises.add(TrainingExercise(trainingId, it))
            duration += (it.minutes * 60) + it.seconds
        }
    }

}