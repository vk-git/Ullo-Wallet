package com.ullo.db.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ullo.api.response.patient.Analyse
import java.util.*

class AnalyseTypeConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<Analyse> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Analyse>>() {

        }.type

        return gson.fromJson<List<Analyse>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<Analyse>): String {
        return gson.toJson(someObjects)
    }
}