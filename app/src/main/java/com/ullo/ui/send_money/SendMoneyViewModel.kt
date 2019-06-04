package com.ullo.ui.send_money

import android.app.Application
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.ullo.api.ResponseListener
import com.ullo.api.response.BaseResponse
import com.ullo.api.service.UlloService
import com.ullo.base.BaseViewModel
import com.ullo.db.DataManager
import com.ullo.utils.Session
import retrofit2.Response


class SendMoneyViewModel(application: Application, ulloService: UlloService, session: Session, dataManager: DataManager) : BaseViewModel<SendMoneyNavigator>(application, ulloService, session, dataManager) {

    fun userSendMoney(registerReq: JsonObject) {
        getCompositeDisposable()?.add(getLinderaService().userSendMoney(registerReq, object : ResponseListener<Response<BaseResponse<JsonElement>>, String> {
            override fun onSuccess(response: Response<BaseResponse<JsonElement>>) {
                if (response.isSuccessful) {
                    response.body()?.run {
                        getSession().setAccountInfo(data)
                        getNavigator()?.onSendMoneySuccess()
                    }
                }
            }

            override fun onInternetConnectionError() {
                //BackGround Call
            }

            override fun onFailure(error: String) {
                //BackGround Call
            }
        }))
    }

}