package com.example.fitnessapp.database.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "exercise")
data class Exercise(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        var name : String,
        var minutes : Int,
        var seconds : Int)







