package com.example.fitnessapp.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(Exercise::class), version = 1)
abstract class ExerciseDatabase : RoomDatabase() {

    abstract fun ExerciseDataDao(): ExerciseDataDao

    companion object {
        private var INSTANCE: ExerciseDatabase? = null

        fun getInstance(context : Context): ExerciseDatabase? {
            if (INSTANCE == null) {
                synchronized(ExerciseDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        ExerciseDatabase::class.java, "exercise.db")
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}