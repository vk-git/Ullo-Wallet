package com.ullo.api.service

import com.google.gson.JsonObject
import com.ullo.api.ApiResponseCallbackWrapper
import com.ullo.api.ResponseListener
import com.ullo.api.response.AppUser
import com.ullo.api.response.BaseResponse
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
}