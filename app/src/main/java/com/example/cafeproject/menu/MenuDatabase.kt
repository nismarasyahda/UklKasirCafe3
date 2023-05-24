package com.example.cafeproject.menu

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Menu::class], version = 3)
abstract class MenuDatabase : RoomDatabase() {
    abstract fun MenuDao(): MenuDao

    companion object{
        private var instance: MenuDatabase? = null

        fun getInstance(context: Context): MenuDatabase{
            if (instance == null){
                instance = Room.databaseBuilder(context, MenuDatabase::class.java, "app-database3")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}