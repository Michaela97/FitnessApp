package com.example.fitnessapp.database.data

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = arrayOf(Exercise::class), version = 1)
abstract class ExerciseDatabase : RoomDatabase() {

    abstract fun exerciseDataDao(): ExerciseDao

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

    private val exerciseDatabaseCallback = object : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            PopulateDbAsync(INSTANCE!!).execute()
        }

        private inner class PopulateDbAsync(val db: ExerciseDatabase) : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                val dao = db.exerciseDataDao()
                dao.deleteAll()
                val exercises = listOf(Exercise(name = "plank", minutes = 1, seconds = 0),
                    Exercise(name = "squat", minutes = 1, seconds = 0),
                    Exercise(name = "push up", minutes = 1, seconds = 0),
                    Exercise(name = "jumps", minutes = 1, seconds = 0),
                    Exercise(name = "crowding", minutes = 1, seconds = 0),
                    Exercise(name = "plank on chair", minutes = 1, seconds = 0),
                    Exercise(name = "jumping squat", minutes = 1, seconds = 0),
                    Exercise(name = "jack plank", minutes = 1, seconds = 0),
                    Exercise(name = "side push up", minutes = 1, seconds = 0),
                    Exercise(name = "yoga push up", minutes = 1, seconds = 0))

                exercises.forEach {dao.insert(it)}

            }
        }
    }
}