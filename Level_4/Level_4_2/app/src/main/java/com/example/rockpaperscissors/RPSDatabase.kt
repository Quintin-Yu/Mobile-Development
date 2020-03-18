package com.example.rockpaperscissors

import android.content.Context
import androidx.room.*

@Database(entities = [RPS::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RPSDatabase : RoomDatabase() {

    abstract fun rpsDao(): RPSDao

    companion object {
        private const val DATABASE_NAME = "RPS_DATABASE"

        @Volatile
        private var rpsRoomDatabaseInstance: RPSDatabase? = null

        fun getDatabase(context: Context): RPSDatabase? {
            if (rpsRoomDatabaseInstance == null) {
                synchronized(RPSDatabase::class.java) {
                    if (rpsRoomDatabaseInstance == null) {
                        rpsRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            RPSDatabase::class.java, DATABASE_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return rpsRoomDatabaseInstance
        }
    }

}
