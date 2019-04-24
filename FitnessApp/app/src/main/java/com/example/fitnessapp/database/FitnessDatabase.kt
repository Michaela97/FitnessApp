package com.example.fitnessapp.database

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.fitnessapp.database.data.Exercise
import com.example.fitnessapp.database.data.FitnessDao
import com.example.fitnessapp.database.data.Training

@Database(entities = [Exercise::class, Training::class], version = 3)
abstract class FitnessDatabase : RoomDatabase() {

    abstract fun fitnessDao(): FitnessDao

    companion object {
        private var INSTANCE: FitnessDatabase? = null

        fun getInstance(context: Context): FitnessDatabase? {
            if (INSTANCE == null) {
                synchronized(FitnessDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FitnessDatabase::class.java, "exercise.db"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .addCallback(exerciseDatabaseCallback)
                        .build()
                }
                InitDbAsync(INSTANCE!!).execute()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }


        private val exerciseDatabaseCallback = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(INSTANCE!!).execute()
            }
        }

        private class PopulateDbAsync(val db: FitnessDatabase) : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                val fitnessDao = db.fitnessDao()
                fitnessDao.deleteAll()
                val exercises = listOf(
                    Exercise(name = "plank"),
                    Exercise(name = "squat"),
                    Exercise(name = "push up"),
                    Exercise(name = "jumps"),
                    Exercise(name = "crowding"),
                    Exercise(name = "plank on chair"),
                    Exercise(name = "jumping squat"),
                    Exercise(name = "jack plank"),
                    Exercise(name = "side push up"),
                    Exercise(name = "yoga push up"),
                    Exercise(name = "plank"),
                    Exercise(name = "squat"),
                    Exercise(name = "push up"),
                    Exercise(name = "jumps"),
                    Exercise(name = "crowding"),
                    Exercise(name = "plank"),
                    Exercise(name = "squat"),
                    Exercise(name = "push up"),
                    Exercise(name = "jumps"),
                    Exercise(name = "crowding")
                )


                val training = Training("Test Training")
                fitnessDao.insertTrainingWithExercises(training, exercises)
                Log.d("FitDB", "Exercises were inserted")
            }
        }
    }
}

private class InitDbAsync(val db: FitnessDatabase) : AsyncTask<Unit, Unit, Unit>() {
    override fun doInBackground(vararg params: Unit?) {
        db.beginTransaction()
        db.endTransaction()
    }
}
