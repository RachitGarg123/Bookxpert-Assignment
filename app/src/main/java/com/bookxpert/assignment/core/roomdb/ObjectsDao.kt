package com.bookxpert.assignment.core.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.bookxpert.assignment.home.data.dataclasses.Objects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

@Dao
interface ObjectsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertObjects(objects: ObjectsEntity)

    @Query("SELECT * FROM objects_entity ORDER BY id DESC LIMIT 1")
    fun getAllObjects(): Flow<ObjectsEntity?>?

    @Delete
    suspend fun deleteObject(objects: ObjectsEntity)

    @Update
    suspend fun updateObject(objects: ObjectsEntity)

    @Transaction
    suspend fun deleteObjectById(objectsId: String) {
        val latestEntity = getAllObjects()?.firstOrNull() ?: return

        val updatedList = latestEntity.objects.filter { it.id != objectsId }

        val updatedEntity = latestEntity.copy(objects = updatedList)

        updateObject(updatedEntity)
    }

    @Transaction
    suspend fun updateObjectById(objectId: String, updatedObject: Objects) {
        val latestEntity = getAllObjects()?.firstOrNull() ?: return

        val updatedList = latestEntity.objects.map { obj ->
            if (obj.id == objectId) updatedObject else obj
        }

        val updatedEntity = latestEntity.copy(objects = updatedList)

        updateObject(updatedEntity)
    }
}