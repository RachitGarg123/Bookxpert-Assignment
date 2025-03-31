package com.bookxpert.assignment.core.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bookxpert.assignment.home.data.dataclasses.Objects

@Entity(tableName = "objects_entity")
data class ObjectsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("objects") val objects: List<Objects>
)