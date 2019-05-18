package com.ullo.api.response

import com.google.gson.annotations.SerializedName

data class HomeMemberShip(@SerializedName("pending")
                          val pending: Boolean,
                          @SerializedName("member")
                          val userHome: UserHome)