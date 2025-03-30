package com.bookxpert.assignment.core.roomdb

import com.bookxpert.assignment.home.data.Objects
import androidx.room.TypeConverter
import com.bookxpert.assignment.core.utility.LogType
import com.bookxpert.assignment.core.utility.printLog
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


class Convertors {

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val type = Types.newParameterizedType(List::class.java, Objects::class.java)
    private val adapter = moshi.adapter<List<Objects>>(type)

    @TypeConverter
    fun fromObjectsList(value: List<Objects>?): String {
        val json = adapter.toJson(value)
        printLog(LogType.DEBUG, "RoomDB", "Converting List<Objects> to JSON: $json")
        return json
    }

    @TypeConverter
    fun toObjectsList(value: String): List<Objects> {
        val list = adapter.fromJson(value) ?: emptyList()
        printLog(LogType.DEBUG, "RoomDB", "Converting JSON to List<Objects>: $list")
        return list
    }
}