package com.example.cafeproject.table

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Meja::class], version = 2)
abstract class TableDatabase : RoomDatabase() {
    abstract fun TableDao(): TableDao

    companion object{
        private var instance: TableDatabase? = null

        fun getInstance(context: Context): TableDatabase{
            if (instance == null){
                instance = Room.databaseBuilder(context, TableDatabase::class.java, "app-database2")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}