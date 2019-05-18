package com.ullo.api.service

import com.google.gson.JsonObject
import com.ullo.api.response.AppUser
import com.ullo.api.response.BaseResponse
import com.ullo.api.response.UserHome
import com.ullo.api.response.patient.Patient
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface UlloApi {

    @POST("users")
    fun userRegister(@Body registerReq: JsonObject): Observable<Response<BaseResponse<AppUser>>>

    @POST("session")
    fun userLogin(@Body loginReq: JsonObject): Observable<Response<BaseResponse<AppUser>>>

    @GET("homes/{homeId}")
    fun userHome(@Path("homeId") homeId: String): Observable<Response<BaseResponse<UserHome>>>

    @GET("patients")
    fun userPatients(): Observable<Response<BaseResponse<List<Patient>>>>

    @GET("homes")
    fun userHomes(@Query("query") query:String): Observable<Response<BaseResponse<List<UserHome>>>>

    @POST("patients")
    fun userCreatePatient(@Body patientReq: JsonObject): Observable<Response<BaseResponse<Patient>>>

    @DELETE("patients/{patientId}")
    fun deletePatient(@Path("patientId") patientId: String): Observable<Response<BaseResponse<Patient>>>

    @DELETE("users/{userId}")
    fun deleteUser(@Path("userId") userId: String): Observable<Response<BaseResponse<AppUser>>>
}