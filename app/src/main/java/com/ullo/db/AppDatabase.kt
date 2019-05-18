package com.ullo.db

import androidx.room.Database
import androidx.room.RoomDatabase

import com.ullo.api.response.patient.Patient
import com.ullo.db.dao.PatientDao

@Database(entities = [Patient::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    internal abstract fun patientDao(): PatientDao
}
