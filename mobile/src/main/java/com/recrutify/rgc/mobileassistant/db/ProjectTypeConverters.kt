package com.recrutify.rgc.mobileassistant.db

import android.arch.persistence.room.TypeConverter
import android.util.Log

object ProjectTypeConverters {
    @TypeConverter
    @JvmStatic
    fun stringToIntList(data: String?): List<Int>? {
        return data?.let {
            it.split(",").map {
                try {
                    it.toInt()
                } catch (ex: NumberFormatException) {
                    Log.e("PTC", "Cannot convert $it to number; $ex")
                    null
                }
            }
        }?.filterNotNull()
    }

    @TypeConverter
    @JvmStatic
    fun intListToString(ints: List<Int>?): String? {
        return ints?.joinToString(",")
    }

    @TypeConverter
    @JvmStatic
    fun stringToStringList(data: String?): List<String>? {
        return data?.let {
            it.split(";")
        }?.filterNotNull()
    }

    @TypeConverter
    @JvmStatic
    fun stringListToString(list: List<String>?): String? {
        return list?.joinToString(";")
    }

}