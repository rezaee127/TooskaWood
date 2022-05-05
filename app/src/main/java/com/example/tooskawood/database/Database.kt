package com.example.tooskawood.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tooskawood.Formula


@Database(entities = [Formula::class], version = 4)
@TypeConverters(Converters::class)
abstract class MyAppDatabase : RoomDatabase() {
    abstract fun formulaDao(): FormulaDao

    companion object {
        @Volatile
        private var INSTANCE: MyAppDatabase? = null

        fun getMyDataBase(context: Context): MyAppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance

            synchronized(MyAppDatabase::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyAppDatabase::class.java, "MyDb"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }


        fun destroyDataBase() {
            INSTANCE = null
        }

    }
}