package com.example.fitnessapp.database.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "challenge")
class Challenge (@PrimaryKey(autoGenerate = true)
                 var id: Int = 0,
                 var title : String)