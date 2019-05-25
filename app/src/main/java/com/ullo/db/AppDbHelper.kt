package com.ullo.db

import androidx.lifecycle.LiveData
import com.ullo.api.response.contact.Contact
import io.reactivex.Observable

class AppDbHelper(private val mAppDatabase: AppDatabase) {

    val allPatients: LiveData<List<Contact>>
        get() = mAppDatabase.contactDao().loadAll()

    fun savePatientList(contactList: List<Contact>): Observable<Boolean> {
        val newList: List<Contact> = ArrayList<Contact>(contactList)
        return Observable.fromCallable {
            newList.map {
                insertOrUpdate(it)
            }
            true
        }
    }

    private fun insertOrUpdate(item: Contact) {
        val id = mAppDatabase.contactDao().getItemId(item.id)
        if (id == null) {
            mAppDatabase.contactDao().insert(item)
        } else {
            mAppDatabase.contactDao().update(item)
        }
    }

    fun savePatient(item: Contact) {
        insertOrUpdate(item)
    }

    fun deletePatientById(patientId: String) {
        mAppDatabase.contactDao().deletePatientById(patientId)
    }
}