package com.ullo.db

import androidx.lifecycle.LiveData
import com.ullo.api.response.patient.Patient
import io.reactivex.Observable

class DataManager(private var appDbHelper: AppDbHelper) {

    val allPatients: LiveData<List<Patient>>
        get() = appDbHelper.allPatients

    fun savePatientList(patientList: List<Patient>): Observable<Boolean> {
        return appDbHelper.savePatientList(patientList)
    }

    fun savePatient(item: Patient) {
        return appDbHelper.savePatient(item)
    }

    fun deletePatientById(patientId: String) {
        return appDbHelper.deletePatientById(patientId)
    }
}
