package com.example.fitnessapp.database.data

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.fitnessapp.database.TrainingDao

@Database(entities = [Exercise::class, Training::class, TrainingExercise::class], version = 2)
abstract class FitnessDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao
    abstract fun trainingDao() : TrainingDao

    companion object {
        private var INSTANCE: FitnessDatabase? = null

        fun getInstance(context: Context): FitnessDatabase? {
            if (INSTANCE == null) {
                synchronized(FitnessDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FitnessDatabase::class.java, "exercise.db"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(exerciseDatabaseCallback)
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }


        private val exerciseDatabaseCallback = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                PopulateDbAsync(INSTANCE!!).execute()
            }
        }

        private class PopulateDbAsync(val db: FitnessDatabase) : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                val exerciseDao = db.exerciseDao()
                val trainingDao = db.trainingDao()

                exerciseDao.deleteAll()
                trainingDao.deleteAll()
                val exercises = listOf(
                    Exercise(name = "plank", minutes = 1, seconds = 0),
                    Exercise(name = "squat", minutes = 1, seconds = 0),
                    Exercise(name = "push up", minutes = 1, seconds = 0),
                    Exercise(name = "jumps", minutes = 1, seconds = 0),
                    Exercise(name = "crowding", minutes = 1, seconds = 0),
                    Exercise(name = "plank on chair", minutes = 1, seconds = 0),
                    Exercise(name = "jumping squat", minutes = 1, seconds = 0),
                    Exercise(name = "jack plank", minutes = 1, seconds = 0),
                    Exercise(name = "side push up", minutes = 1, seconds = 0),
                    Exercise(name = "yoga push up", minutes = 1, seconds = 0)
                )
                exercises.forEach { exerciseDao.insert(it) }

                val training = Training("Test Training")
                training.addExercises(exercises)

                trainingDao.insert(training)

                val trainings = trainingDao.findAll().value
                trainings?.forEach { Log.d("Trainings", it.toString()) }
                Log.d("Trainings", trainings?.toString())

            }
        }
    }
}