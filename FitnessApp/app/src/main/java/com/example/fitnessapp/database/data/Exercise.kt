package com.example.fitnessapp.database.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "exercise")
data class Exercise(
    var name: String,
    var minutes: Int,
    var seconds: Int,
   //s var trainingId: Int = 0,
    @PrimaryKey(autoGenerate = true)
    var exerciseId: Int = 0
)







