package com.example.fitnessapp.data.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "exercise_type")
data class Exercise(@PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo(name = "exercise_name") var name : String,
        @ColumnInfo(name = "time") var time : String) {

        constructor():this(null,"","")
}





