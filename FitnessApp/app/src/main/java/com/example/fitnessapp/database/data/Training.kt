package com.example.fitnessapp.database.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "training")
data class Training(
    var title: String,
    @PrimaryKey(autoGenerate = true)
    var trainingId: Int = 0
)

