package com.ullo.api.service

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.ullo.api.response.AppUser
import com.ullo.api.response.BaseResponse
import com.ullo.api.response.CmsData
import com.ullo.api.response.balance_history.BalanceHistoryData
import com.ullo.api.response.contact.ContactData
import com.ullo.api.response.notification.NotificationData
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UlloApi {

    @POST("auth/register")
    fun userRegister(@Body registerReq: JsonObject): Observable<Response<BaseResponse<AppUser>>>

    @POST("auth/login")
    fun userLogin(@Body loginReq: JsonObject): Observable<Response<BaseResponse<AppUser>>>

    @POST("auth/forgotpassword")
    fun userForgotPassword(@Body forgotReq: JsonObject): Observable<Response<BaseResponse<AppUser>>>

    @POST("auth/change-password")
    fun userChangePassword(@Body changePasswordReq: JsonObject): Observable<Response<BaseResponse<AppUser>>>

    @POST("auth/edit-profile")
    fun userUpdateProfile(@Body changePasswordReq: JsonObject): Observable<Response<BaseResponse<AppUser>>>

    @GET("cms/2/")
    fun userCMS(): Observable<Response<BaseResponse<CmsData>>>

    @POST("contactus")
    fun userContactUs(@Body registerReq: JsonObject): Observable<Response<BaseResponse<JsonElement>>>

    @POST("contactlist")
    fun userContactlist(@Body registerReq: JsonObject): Observable<Response<BaseResponse<ContactData>>>

    @POST("notification/list")
    fun userNotificationlist(): Observable<Response<BaseResponse<NotificationData>>>

    @POST("balance-history")
    fun userBalanceHistorylist(): Observable<Response<BaseResponse<BalanceHistoryData>>>

    @POST("add-money")
    fun userAddMoney(@Body registerReq: JsonObject): Observable<Response<BaseResponse<JsonElement>>>

    @POST("send-money")
    fun userSendMoney(@Body registerReq: JsonObject): Observable<Response<BaseResponse<JsonElement>>>

    @GET("account-info")
    fun userAccountInfo(): Observable<Response<BaseResponse<JsonElement>>>
}