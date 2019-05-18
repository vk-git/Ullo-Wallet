package com.ullo.db.dao;

import com.ullo.api.response.patient.Patient;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PatientDao {

    @Query("SELECT id FROM patient WHERE id = :id LIMIT 1")
    String getItemId(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Patient patient);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int update(Patient patient);

    @Query("DELETE FROM patient WHERE id = :patientId")
    int deletePatientById(String patientId);

    @Query("SELECT * FROM patient")
    LiveData<List<Patient>> loadAll();
}
