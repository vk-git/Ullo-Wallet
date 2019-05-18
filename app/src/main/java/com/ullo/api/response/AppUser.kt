package com.ullo.api.response

import com.google.gson.annotations.SerializedName

data class AppUser(@SerializedName("firstname")
                   val firstname: String,
                   @SerializedName("role")
                   val role: String,
                   @SerializedName("deleteAt")
                   val deleteAt: String,
                   @SerializedName("language")
                   val language: String,
                   @SerializedName("userrole")
                   val userrole: String,
                   @SerializedName("companyMemberShip")
                   val companyMemberShip: CompanyMemberShip,
                   @SerializedName("OrganisationMemberShip")
                   val organisationMemberShip: OrganisationMemberShip,
                   @SerializedName("lastname")
                   val lastname: String,
                   @SerializedName("phone")
                   val phone: String,
                   @SerializedName("__v")
                   val V: Int,
                   @SerializedName("secretTokenPw")
                   val secretTokenPw: String,
                   @SerializedName("_id")
                   val Id: String,
                   @SerializedName("homeMemberShip")
                   val homeMemberShip: HomeMemberShip,
                   @SerializedName("email")
                   val email: String,
                   @SerializedName("status")
                   val status: String,
                   @SerializedName("username")
                   val username: String,
                   @SerializedName("timestamp")
                   val timestamp: String,
                   @SerializedName("home")
                   val userHome: UserHome)