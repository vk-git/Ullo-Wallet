package com.ullo.api.response.contact;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "contact")
public class Contact implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public Integer pid;

    @SerializedName("_id")
    public String id;
    @SerializedName("fullName")
    public String fullName;
    @SerializedName("mobileNo")
    public String mobileNo;


    public final static Parcelable.Creator<Contact> CREATOR = new Creator<Contact>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        public Contact[] newArray(int size) {
            return (new Contact[size]);
        }

    };

    protected Contact(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.fullName = ((String) in.readValue((String.class.getClassLoader())));
        this.mobileNo = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Contact() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(fullName);
        dest.writeValue(mobileNo);
    }

    public int describeContents() {
        return 0;
    }

}