package com.ullo.ui.balance_history

import android.app.Application
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.ullo.App
import com.ullo.R
import com.ullo.api.ResponseListener
import com.ullo.api.response.BaseResponse
import com.ullo.api.response.balance_history.BalanceHistoryData
import com.ullo.api.response.notification.NotificationData
import com.ullo.api.service.UlloService
import com.ullo.base.BaseViewModel
import com.ullo.db.DataManager
import com.ullo.utils.Session
import com.ullo.utils.SharedPreferenceHelper
import retrofit2.Response


class BalanceHistoryViewModel(application: Application, ulloService: UlloService, session: Session, dataManager: DataManager) : BaseViewModel<BalanceHistoryNavigator>(application, ulloService, session, dataManager) {

    fun userBalanceHistorylist() {
        App.instance.showLoadingOverlayDialog(App.instance.getString(R.string.loading))
        getCompositeDisposable()?.add(getLinderaService().userBalanceHistorylist(object : ResponseListener<Response<BaseResponse<BalanceHistoryData>>, String> {
            override fun onSuccess(response: Response<BaseResponse<BalanceHistoryData>>) {
                App.instance.hideLoadingOverlayDialog()
                if (response.isSuccessful) {
                    response.body()?.run {
                        getNavigator()?.onBalanceHistorySuccessfull(data)
                    }
                } else {
                    try {
                        response.errorBody()?.run {
                            val errorResponse = SharedPreferenceHelper.getObjectFromString(string(), object : TypeToken<BaseResponse<JsonElement>>() {})
                            getNavigator()?.handleError(errorResponse.error.asJsonArray[0].asString)
                        }
                    } catch (e: Exception) {
                        getNavigator()?.handleError(e.message!!)
                    }
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