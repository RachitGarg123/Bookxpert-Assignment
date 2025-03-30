package com.bookxpert.assignment.core.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ObjectsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertObjects(objects: ObjectsEntity)

    @Query("SELECT * FROM objects_entity ORDER BY id DESC LIMIT 1")
    fun getAllObjects(): Flow<ObjectsEntity>

    @Delete
    suspend fun deleteObject(objects: ObjectsEntity)
}