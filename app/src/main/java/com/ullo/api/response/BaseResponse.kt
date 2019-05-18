package com.ullo.api.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(@SerializedName("confirmation")
                           val confirmation: String,
                           @SerializedName(value = "message", alternate = ["error","user","data"])
                           val data: T,
                           @SerializedName("token")
                           val token: String)