package com.ullo.db

import androidx.lifecycle.LiveData
import com.ullo.api.response.patient.Patient
import io.reactivex.Observable

class AppDbHelper(private val mAppDatabase: AppDatabase) {

    val allPatients: LiveData<List<Patient>>
        get() =  mAppDatabase.patientDao().loadAll()

    fun savePatientList(patientList: List<Patient>): Observable<Boolean> {
        val newList: List<Patient> = ArrayList<Patient>(patientList)
        return Observable.fromCallable {
            newList.map {
                insertOrUpdate(it)
            }
            true
        }
    }

    private fun insertOrUpdate(item: Patient) {
        val id = mAppDatabase.patientDao().getItemId(item.id)
        if (id == null) {
            mAppDatabase.patientDao().insert(item)
        } else {
            mAppDatabase.patientDao().update(item)
        }
    }

    fun savePatient(item: Patient) {
        insertOrUpdate(item)
    }

    fun deletePatientById(patientId: String) {
        mAppDatabase.patientDao().deletePatientById(patientId)
    }
}