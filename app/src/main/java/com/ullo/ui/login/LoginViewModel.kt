package com.ullo.ui.login

import android.app.Application
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.ullo.App
import com.ullo.R
import com.ullo.api.ResponseListener
import com.ullo.api.response.AppUser
import com.ullo.api.response.BaseResponse
import com.ullo.api.service.UlloService
import com.ullo.base.BaseViewModel
import com.ullo.db.DataManager
import com.ullo.utils.Session
import retrofit2.Response

class LoginViewModel(application: Application, ulloService: UlloService, session: Session, dataManager: DataManager) : BaseViewModel<LoginNavigator>(application, ulloService, session, dataManager) {

    fun onLoginButtonClick() {
        getNavigator()?.onLoginHandle()
    }

    fun onForgotPasswordButtonClick() {
        getNavigator()?.onForgotPasswordHandle()
    }

    fun login(loginReq: JsonObject) {
        App.instance.showLoadingOverlayDialog(App.instance.getString(R.string.loading))
        getCompositeDisposable()?.add(getLinderaService().userLogin(loginReq, object : ResponseListener<Response<BaseResponse<AppUser>>, String> {
            override fun onSuccess(response: Response<BaseResponse<AppUser>>) {
                App.instance.hideLoadingOverlayDialog()
                if (response.isSuccessful) {
                    response.body()?.let {
                        getSession().setAppUser(it.data)
                        getSession().setAppUserToken(it.data.token.accessToken)
                    }
                    getNavigator()?.onMainScreen()
                } else {
                    response.body()?.run {
                        Log.d("mytag", "Response ::" + Gson().toJson(this))
                    }
                    response.errorBody()?.run {
                        Log.d("mytag", "Error ::" + Gson().toJson(this))
                    }
                    /*val errorResponse = SharedPreferenceHelper.getObjectFromString(response.errorBody()!!.string(), object : TypeToken<BaseResponse<String>>() {})
                    getNavigator()?.handleError(errorResponse.data)*/
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