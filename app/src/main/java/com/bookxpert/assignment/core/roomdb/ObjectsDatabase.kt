package com.bookxpert.assignment.core.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ObjectsEntity::class], version = 1)
@TypeConverters(Convertors::class)
abstract class ObjectsDatabase: RoomDatabase() {
    abstract fun objectsDao(): ObjectsDao
    companion object {
        @Volatile
        var INSTANCE: ObjectsDatabase? = null
        fun getInstance(context: Context): ObjectsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ObjectsDatabase::class.java,
                    "objects_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}