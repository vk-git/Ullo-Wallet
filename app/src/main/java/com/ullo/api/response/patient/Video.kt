package com.ullo.api.response.patient

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Video(@SerializedName("stage")
                     var stage: String?,
                 @SerializedName("deleteAt")
                     var deleteAt: String?,
                 @SerializedName("patientID")
                     var patientID: String?,
                 @SerializedName("__v")
                     var V: Int,
                 @SerializedName("pathOBS")
                     var pathOBS: String?,
                 @SerializedName("_id")
                     var Id: String?,
                 @SerializedName("type")
                     var type: String?,
                 @SerializedName("userID")
                     var userID: String?,
                 @SerializedName("localFilePath")
                     var localFilePath: String?,
                 @SerializedName("timestamp")
                     var timestamp: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(stage)
        parcel.writeString(deleteAt)
        parcel.writeString(patientID)
        parcel.writeInt(V)
        parcel.writeString(pathOBS)
        parcel.writeString(Id)
        parcel.writeString(type)
        parcel.writeString(userID)
        parcel.writeString(localFilePath)
        parcel.writeString(timestamp)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Video> {
        override fun createFromParcel(parcel: Parcel): Video {
            return Video(parcel)
        }

        override fun newArray(size: Int): Array<Video?> {
            return arrayOfNulls(size)
        }
    }
}