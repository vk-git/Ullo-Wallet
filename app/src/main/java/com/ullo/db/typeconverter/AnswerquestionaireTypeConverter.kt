package com.ullo.db.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ullo.api.response.patient.Answerquestionaire
import java.util.*

class AnswerquestionaireTypeConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<Answerquestionaire> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Answerquestionaire>>() {

        }.type

        return gson.fromJson<List<Answerquestionaire>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<Answerquestionaire>): String {
        return gson.toJson(someObjects)
    }
}