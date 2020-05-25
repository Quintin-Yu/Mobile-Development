package com.example.mypersonalnotepad.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class NotepadRoomDatabase : RoomDatabase() {

    abstract fun noteDao() : NoteDao

    companion object {
        private const val DATABASE_NAME = "NOTEPAD_DATABASE"

        @Volatile
        private var INSTANCE: NotepadRoomDatabase? = null

        fun getDatabase(context: Context) : NotepadRoomDatabase? {
            if(INSTANCE == null) {
                synchronized(NotepadRoomDatabase::class.java){
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            NotepadRoomDatabase::class.java,
                            DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }

            return INSTANCE
        }
    }
}