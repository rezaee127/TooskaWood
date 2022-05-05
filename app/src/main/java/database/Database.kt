package database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tooskawood.Formula

@Database(entities = [Formula::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun formulaDao(): FormulaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getMyDataBase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance

            synchronized(AppDatabase::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "MyDb"
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