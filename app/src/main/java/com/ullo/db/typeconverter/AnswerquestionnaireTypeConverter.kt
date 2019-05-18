package com.ullo.db.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ullo.api.response.patient.Answerquestionnaire
import java.util.*

class AnswerquestionnaireTypeConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<Answerquestionnaire> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Answerquestionnaire>>() {

        }.type

        return gson.fromJson<List<Answerquestionnaire>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<Answerquestionnaire>): String {
        return gson.toJson(someObjects)
    }
}