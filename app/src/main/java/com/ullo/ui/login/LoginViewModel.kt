package com.ullo.ui.login

import android.app.Application
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.ullo.api.ResponseListener
import com.ullo.api.response.AppUser
import com.ullo.api.response.BaseResponse
import com.ullo.api.service.UlloService
import com.ullo.base.BaseViewModel
import com.ullo.db.DataManager
import com.ullo.utils.Session
import com.ullo.utils.SharedPreferenceHelper
import retrofit2.Response

class LoginViewModel(application: Application, ulloService: UlloService, session: Session, dataManager: DataManager) : BaseViewModel<LoginNavigator>(application, ulloService, session, dataManager) {

    fun onLoginButtonClick() {
        getNavigator()?.onLoginHandle()
    }

    fun onForgotPasswordButtonClick() {
        getNavigator()?.onForgotPasswordHandle()
    }

    fun login(loginReq: JsonObject) {
        getCompositeDisposable()?.add(getLinderaService().userLogin(loginReq, object : ResponseListener<Response<BaseResponse<AppUser>>, String> {
            override fun onSuccess(response: Response<BaseResponse<AppUser>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        getSession().setAppUser(it.data)
                        getSession().setAppUserToken(it.token)
                    }
                    getNavigator()?.onMainScreen()
                } else {
                    val errorResponse = SharedPreferenceHelper.getObjectFromString(response.errorBody()!!.string(), object : TypeToken<BaseResponse<String>>() {})
                    getNavigator()?.handleError(errorResponse.data)
                }
            }

            override fun onInternetConnectionError() {
                getNavigator()?.onInternetConnectionError()
            }

            override fun onFailure(error: String) {
                getNavigator()?.handleError(error)
            }
        }))
    }
}