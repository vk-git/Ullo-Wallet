package com.ullo.api.response.patient

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Answerquestionaire(@SerializedName("patientID")
                              var patientID: String?,
                              @SerializedName("__v")
                              var V: Int,
                              @SerializedName("questionnaireID")
                              var questionnaireID: String?,
                              @SerializedName("_id")
                              var Id: String?,
                              @SerializedName("userID")
                              var userID: String?,
                              @SerializedName("timestamp")
                              var timestamp: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(patientID)
        parcel.writeInt(V)
        parcel.writeString(questionnaireID)
        parcel.writeString(Id)
        parcel.writeString(userID)
        parcel.writeString(timestamp)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Answerquestionaire> {
        override fun createFromParcel(parcel: Parcel): Answerquestionaire {
            return Answerquestionaire(parcel)
        }

        override fun newArray(size: Int): Array<Answerquestionaire?> {
            return arrayOfNulls(size)
        }
    }
}