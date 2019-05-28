package com.ullo.api.service

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.ullo.api.ApiResponseCallbackWrapper
import com.ullo.api.ResponseListener
import com.ullo.api.response.AppUser
import com.ullo.api.response.BaseResponse
import com.ullo.api.response.CmsData
import com.ullo.api.response.contact.ContactData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class UlloService(private val ulloApi: UlloApi) {

    fun userRegister(registerReq: JsonObject, listener: ResponseListener<Response<BaseResponse<AppUser>>, String>): Disposable {
        return ulloApi.userRegister(registerReq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : ApiResponseCallbackWrapper<Response<BaseResponse<AppUser>>>() {

                    override fun onSuccess(response: Response<BaseResponse<AppUser>>) {
                        listener.onSuccess(response)
                    }

                    override fun onInternetConnectionError() {
                        listener.onInternetConnectionError()
                    }

                    override fun onFailure(error: String) {
                        listener.onFailure(error)
                    }
                })
    }

    fun userLogin(loginReq: JsonObject, listener: ResponseListener<Response<BaseResponse<AppUser>>, String>): Disposable {
        return ulloApi.userLogin(loginReq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : ApiResponseCallbackWrapper<Response<BaseResponse<AppUser>>>() {

                    override fun onSuccess(response: Response<BaseResponse<AppUser>>) {
                        listener.onSuccess(response)
                    }

                    override fun onInternetConnectionError() {
                        listener.onInternetConnectionError()
                    }

                    override fun onFailure(error: String) {
                        listener.onFailure(error)
                    }
                })
    }

    fun userForgotPassword(loginReq: JsonObject, listener: ResponseListener<Response<BaseResponse<AppUser>>, String>): Disposable {
        return ulloApi.userForgotPassword(loginReq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : ApiResponseCallbackWrapper<Response<BaseResponse<AppUser>>>() {

                    override fun onSuccess(response: Response<BaseResponse<AppUser>>) {
                        listener.onSuccess(response)
                    }

                    override fun onInternetConnectionError() {
                        listener.onInternetConnectionError()
                    }

                    override fun onFailure(error: String) {
                        listener.onFailure(error)
                    }
                })
    }

    fun userChangePassword(changePasswordReq: JsonObject, listener: ResponseListener<Response<BaseResponse<AppUser>>, String>): Disposable {
        return ulloApi.userChangePassword(changePasswordReq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : ApiResponseCallbackWrapper<Response<BaseResponse<AppUser>>>() {

                    override fun onSuccess(response: Response<BaseResponse<AppUser>>) {
                        listener.onSuccess(response)
                    }

                    override fun onInternetConnectionError() {
                        listener.onInternetConnectionError()
                    }

                    override fun onFailure(error: String) {
                        listener.onFailure(error)
                    }
                })
    }

    fun userUpdateProfile(registerReq: JsonObject, listener: ResponseListener<Response<BaseResponse<AppUser>>, String>): Disposable {
        return ulloApi.userUpdateProfile(registerReq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : ApiResponseCallbackWrapper<Response<BaseResponse<AppUser>>>() {

                    override fun onSuccess(response: Response<BaseResponse<AppUser>>) {
                        listener.onSuccess(response)
                    }

                    override fun onInternetConnectionError() {
                        listener.onInternetConnectionError()
                    }

                    override fun onFailure(error: String) {
                        listener.onFailure(error)
                    }
                })
    }

    fun userCMS(listener: ResponseListener<Response<BaseResponse<CmsData>>, String>): Disposable {
        return ulloApi.userCMS()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : ApiResponseCallbackWrapper<Response<BaseResponse<CmsData>>>() {

                    override fun onSuccess(response: Response<BaseResponse<CmsData>>) {
                        listener.onSuccess(response)
                    }

                    override fun onInternetConnectionError() {
                        listener.onInternetConnectionError()
                    }

                    override fun onFailure(error: String) {
                        listener.onFailure(error)
                    }
                })
    }

    fun userContactUs(registerReq: JsonObject, listener: ResponseListener<Response<BaseResponse<JsonElement>>, String>): Disposable {
        return ulloApi.userContactUs(registerReq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : ApiResponseCallbackWrapper<Response<BaseResponse<JsonElement>>>() {

                    override fun onSuccess(response: Response<BaseResponse<JsonElement>>) {
                        listener.onSuccess(response)
                    }

                    override fun onInternetConnectionError() {
                        listener.onInternetConnectionError()
                    }

                    override fun onFailure(error: String) {
                        listener.onFailure(error)
                    }
                })
    }

    fun userContactlist(registerReq: JsonObject, listener: ResponseListener<Response<BaseResponse<ContactData>>, String>): Disposable {
        return ulloApi.userContactlist(registerReq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : ApiResponseCallbackWrapper<Response<BaseResponse<ContactData>>>() {

                    override fun onSuccess(response: Response<BaseResponse<ContactData>>) {
                        listener.onSuccess(response)
                    }

                    override fun onInternetConnectionError() {
                        listener.onInternetConnectionError()
                    }

                    override fun onFailure(error: String) {
                        listener.onFailure(error)
                    }
                })
    }
}