package com.tridiv.tridivroymisltest.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tridiv.tridivroymisltest.data.model.TvDaoItem

@Database(entities = [TvDaoItem::class], version = 1)
abstract class AppDatabase :RoomDatabase() {
    abstract fun getTvDao(): TvDataDao

//    companion object {
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getDB(context: Context): AppDatabase {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) return tempInstance
//
//            kotlin.synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext, AppDatabase::class.java, "tv-info-db"
//                ).allowMainThreadQueries().build()
//                INSTANCE = instance
//                return instance
//            }
//
//        }
//
//    }
}