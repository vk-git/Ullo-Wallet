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

    @Query("SELECT pid FROM contact WHERE pid = :id LIMIT 1")
    Integer getItemId(Integer id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Contact contact);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int update(Contact contact);

    @Query("DELETE FROM contact WHERE pid = :contactId")
    int deletePatientById(Integer contactId);

    @Query("SELECT * FROM contact")
    LiveData<List<Contact>> loadAll();
}
