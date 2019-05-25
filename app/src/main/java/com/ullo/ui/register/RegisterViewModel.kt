package com.ullo.ui.register

import android.app.Application
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.ullo.App
import com.ullo.R
import com.ullo.api.ResponseListener
import com.ullo.api.response.AppUser
import com.ullo.api.response.BaseResponse
import com.ullo.api.service.UlloService
import com.ullo.base.BaseViewModel
import com.ullo.db.DataManager
import com.ullo.utils.Session
import com.ullo.utils.SharedPreferenceHelper
import retrofit2.Response


class RegisterViewModel(application: Application, ulloService: UlloService, session: Session, dataManager: DataManager) : BaseViewModel<RegisterNavigator>(application, ulloService, session, dataManager) {

    fun onLoginButtonClick() {
        getNavigator()!!.onLoginScreen()
    }

    fun onRegisterButtonClick() {
        getNavigator()!!.onCheckValidation()
    }

    fun register(registerReq: JsonObject) {
        App.instance.showLoadingOverlayDialog(App.instance.getString(R.string.loading))
        getCompositeDisposable()?.add(getLinderaService().userRegister(registerReq, object : ResponseListener<Response<BaseResponse<AppUser>>, String> {
            override fun onSuccess(response: Response<BaseResponse<AppUser>>) {
                App.instance.hideLoadingOverlayDialog()
                if (response.isSuccessful) {
                    response.body()?.let {
                        getSession().setAppUser(it.data)
                        getSession().setAppUserToken(it.data.token.accessToken)
                        getNavigator()?.onRegisterSuccessful(it.data)
                    }
                } else {
                    val errorResponse = SharedPreferenceHelper.getObjectFromString(response.errorBody()!!.string(), object : TypeToken<BaseResponse<String>>() {})
                    getNavigator()?.handleError(errorResponse.data)
                }
            }

            override fun onInternetConnectionError() {
                App.instance.hideLoadingOverlayDialog()
                getNavigator()?.onInternetConnectionError()
            }

            override fun onFailure(error: String) {
                App.instance.hideLoadingOverlayDialog()
                getNavigator()?.handleError(error)
            }
        }))
    }
}