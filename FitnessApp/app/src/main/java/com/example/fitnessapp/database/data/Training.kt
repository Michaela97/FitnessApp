package com.example.fitnessapp.database.data

import androidx.room.Entity

@Entity(tableName = "training")
class Training(
                var challenge: Challenge,
                var exercise: Exercise,
                var duration: Int
                )