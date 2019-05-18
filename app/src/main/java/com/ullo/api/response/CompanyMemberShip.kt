package com.ullo.api.response

import com.google.gson.annotations.SerializedName

data class CompanyMemberShip(@SerializedName("pending")
                             val pending: Boolean)