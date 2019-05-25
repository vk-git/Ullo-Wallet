package com.ullo.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ullo.api.response.contact.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT id FROM contact WHERE id = :id LIMIT 1")
    String getItemId(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Contact contact);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int update(Contact contact);

    @Query("DELETE FROM contact WHERE id = :contactId")
    int deletePatientById(String contactId);

    @Query("SELECT * FROM contact")
    LiveData<List<Contact>> loadAll();
}
