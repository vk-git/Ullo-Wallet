package com.ullo.db.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ullo.api.response.patient.Video
import java.util.*

class VideoTypeConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<Video> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Video>>() {

        }.type

        return gson.fromJson<List<Video>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<Video>): String {
        return gson.toJson(someObjects)
    }
}