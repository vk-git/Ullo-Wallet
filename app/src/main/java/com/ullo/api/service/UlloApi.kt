package com.ullo.api.service

import com.google.gson.JsonObject
import com.ullo.api.response.AppUser
import com.ullo.api.response.BaseResponse
import com.ullo.api.response.CmsData
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
}