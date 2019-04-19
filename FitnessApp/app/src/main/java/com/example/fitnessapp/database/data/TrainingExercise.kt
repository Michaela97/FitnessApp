package com.example.fitnessapp.database.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "training_exercise")
data class TrainingExercise(
    var trainingId: Int,
    var exerciseId: Int,
    @PrimaryKey(autoGenerate = true)
    var teId : Int = 0
)