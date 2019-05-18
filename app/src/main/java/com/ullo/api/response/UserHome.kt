package com.ullo.api.response

import com.google.gson.annotations.SerializedName

data class UserHome(@SerializedName("zip")
                    val zip: String,
                    @SerializedName("city")
                    val city: String,
                    @SerializedName("street")
                    val street: String,
                    @SerializedName("__v")
                    val V: Int,
                    @SerializedName("name")
                    val name: String,
                    @SerializedName("countrycodeISO316a2")
                    val countrycodeISOA: String,
                    @SerializedName("language")
                    val language: String,
                    @SerializedName("_id")
                    val Id: String,
                    @SerializedName("status")
                    val status: String,
                    @SerializedName("timestamp")
                    val timestamp: String,
                    @SerializedName("settings")
                    val settings: Any)