package com.example.tryaq.data.local

import androidx.room.TypeConverter
import com.example.tryaq.domain.model.models.Ad
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.StringBuilder

class DatabaseConverter {
    private val separator = ","
    var gson = Gson()

    @TypeConverter
    fun convertListToString(list: List<String>): String {
        val stringBuilder = StringBuilder()
        for (item in list) {
            stringBuilder.append(item).append(separator)
        }

        stringBuilder.setLength(stringBuilder.length - separator.length)
        return stringBuilder.toString()
    }

    @TypeConverter
    fun convertStringToList(string: String): List<String> {
        return string.split(separator)
    }

    @TypeConverter
    fun adsToString(ads: List<Ad>): String {
        return gson.toJson(ads)
    }

    @TypeConverter
    fun stringToAds(data: String): List<Ad> {
        val listType = object : TypeToken<List<Ad>>() {}.type
        return gson.fromJson(data, listType)
    }
}