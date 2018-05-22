package com.recrutify.rgc.mobileassistant.db

import android.arch.persistence.room.TypeConverter
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.recrutify.rgc.mobileassistant.Model.Label
import retrofit2.converter.gson.GsonConverterFactory

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

    @TypeConverter
    @JvmStatic
    fun labelListToString(list: List<Label>?): String? {
        val gson = Gson()

        return gson.toJson(list)
    }

    @TypeConverter
    @JvmStatic
    fun stringToLabelList(data: String): List<Label>? {
        val gson = Gson()
        return gson.fromJson<List<Label>>(data)
    }

    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)
}