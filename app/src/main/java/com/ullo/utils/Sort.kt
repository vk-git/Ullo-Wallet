package com.ullo.utils

import com.ullo.api.response.patient.Patient
import java.util.*


object Sort {

    fun onPatientList(list: List<Patient>) {
        Collections.sort(list) { o1, o2 ->
            val a1 = o1.lastname.toUpperCase()
            val b1 = o2.lastname.toUpperCase()
            val c1 = o1.firstname.toUpperCase()
            val d1 = o2.firstname.toUpperCase()

            if (c1.matches("\\d+".toRegex()) && d1.matches("\\d+".toRegex())) {
                val a = c1.toInt()
                val b = d1.toInt()
                if (a < b) -1
                if (a > b) 1
                0
            }

            if (a1 > b1) {
                1
            } else if (a1 < b1) {
                -1
            }
            0
        }
    }
}