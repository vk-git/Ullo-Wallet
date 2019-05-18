package com.ullo.api.response.patient

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Analyse(@SerializedName("sequenceNumber")
                   var sequenceNumber: Int = 0,
                   @SerializedName("patientID")
                   var patientID: String?,
                   @SerializedName("dispatchDate")
                   var dispatchDate: String?,
                   @SerializedName("language")
                   var language: String?,
                   @SerializedName("videoID")
                   var videoID: String?,
                   @SerializedName("params")
                   var params: String?,
                   @SerializedName("userID")
                   var userID: String?,
                   @SerializedName("answerQuestionaireID")
                   var answerQuestionaireID: String?,
                   @SerializedName("score")
                   var score: String?,
                   @SerializedName("__v")
                   var V: Int,
                   @SerializedName("answerQuestionnaireID")
                   var answerQuestionnaireID: String?,
                   @SerializedName("submittedByUser")
                   var submittedByUser: Boolean = false,
                   @SerializedName("_id")
                   var Id: String?,
                   @SerializedName("homeID")
                   var homeID: String?,
                   @SerializedName("status")
                   var status: String?,
                   @SerializedName("timestamp")
                   var timestamp: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(sequenceNumber)
        parcel.writeString(patientID)
        parcel.writeString(dispatchDate)
        parcel.writeString(language)
        parcel.writeString(videoID)
        parcel.writeString(params)
        parcel.writeString(userID)
        parcel.writeString(answerQuestionaireID)
        parcel.writeString(score)
        parcel.writeInt(V)
        parcel.writeString(answerQuestionnaireID)
        parcel.writeByte(if (submittedByUser) 1 else 0)
        parcel.writeString(Id)
        parcel.writeString(homeID)
        parcel.writeString(status)
        parcel.writeString(timestamp)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Analyse> {
        override fun createFromParcel(parcel: Parcel): Analyse {
            return Analyse(parcel)
        }

        override fun newArray(size: Int): Array<Analyse?> {
            return arrayOfNulls(size)
        }
    }
}