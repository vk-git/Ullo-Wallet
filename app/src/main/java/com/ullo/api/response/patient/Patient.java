package com.ullo.api.response.patient;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;
import com.ullo.db.typeconverter.AnalyseTypeConverter;
import com.ullo.db.typeconverter.AnswerquestionaireTypeConverter;
import com.ullo.db.typeconverter.AnswerquestionnaireTypeConverter;
import com.ullo.db.typeconverter.VideoTypeConverter;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "patient")
public class Patient implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public Integer pid;

    @SerializedName("_id")
    public String id;
    @SerializedName("age")
    public Integer age;
    @SerializedName("height")
    public Integer height;
    @SerializedName("weight")
    public Integer weight;
    @SerializedName("hasDementia")
    public Boolean hasDementia;
    @SerializedName("residential_form")
    public String residentialForm;
    @SerializedName("firstname")
    public String firstname;
    @SerializedName("lastname")
    public String lastname;
    @SerializedName("sex")
    public String sex;
    @SerializedName("userID")
    public String userID;
    @TypeConverters(AnalyseTypeConverter.class)
    @SerializedName("analyse")
    public List<Analyse> analyse = new ArrayList<>();
    @TypeConverters(AnswerquestionaireTypeConverter.class)
    @SerializedName("answerquestionaire")
    public List<Answerquestionaire> answerquestionaire = new ArrayList<>();
    @TypeConverters(AnswerquestionnaireTypeConverter.class)
    @SerializedName("answerquestionnaire")
    public List<Answerquestionnaire> answerquestionnaire = new ArrayList<>();
    @TypeConverters(VideoTypeConverter.class)
    @SerializedName("video")
    public List<Video> video = new ArrayList<>();
    public final static Parcelable.Creator<Patient> CREATOR = new Creator<Patient>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Patient createFromParcel(Parcel in) {
            return new Patient(in);
        }

        public Patient[] newArray(int size) {
            return (new Patient[size]);
        }

    };

    protected Patient(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.age = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.height = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.weight = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.hasDementia = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.residentialForm = ((String) in.readValue((String.class.getClassLoader())));
        this.firstname = ((String) in.readValue((String.class.getClassLoader())));
        this.lastname = ((String) in.readValue((String.class.getClassLoader())));
        this.sex = ((String) in.readValue((String.class.getClassLoader())));
        this.userID = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.analyse, (Analyse.class.getClassLoader()));
        in.readList(this.answerquestionaire, (Answerquestionaire.class.getClassLoader()));
        in.readList(this.answerquestionnaire, (Answerquestionnaire.class.getClassLoader()));
        in.readList(this.video, (Video.class.getClassLoader()));
    }

    public Patient() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(age);
        dest.writeValue(height);
        dest.writeValue(weight);
        dest.writeValue(hasDementia);
        dest.writeValue(residentialForm);
        dest.writeValue(firstname);
        dest.writeValue(lastname);
        dest.writeValue(sex);
        dest.writeValue(userID);
        dest.writeList(analyse);
        dest.writeList(answerquestionaire);
        dest.writeList(answerquestionnaire);
        dest.writeList(video);
    }

    public int describeContents() {
        return 0;
    }

}