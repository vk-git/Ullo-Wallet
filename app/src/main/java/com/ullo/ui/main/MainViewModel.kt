package com.ullo.ui.main

import android.app.Application
import android.util.Log
import com.github.tamir7.contacts.Contacts
import com.google.gson.JsonElement
import com.ullo.api.ResponseListener
import com.ullo.api.response.BaseResponse
import com.ullo.api.response.CmsData
import com.ullo.api.response.contact.Contact
import com.ullo.api.service.UlloService
import com.ullo.base.BaseViewModel
import com.ullo.db.DataManager
import com.ullo.utils.Session
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response


class MainViewModel(application: Application, ulloService: UlloService, session: Session, dataManager: DataManager) : BaseViewModel<MainNavigator>(application, ulloService, session, dataManager) {


    fun onMenuButtonClick() {
        getNavigator()?.onMenuHandle()
    }

    fun onNotificationButtonClick() {
        getNavigator()?.onNotificationHandle()
    }

    fun onSendMoneyButtonClick() {
        getNavigator()?.onSendMoneyHandle()
    }

    fun onReceiveMoneyButtonClick() {
        getNavigator()?.onReceiveMoneyHandle()
    }

    fun getCms() {
        getCompositeDisposable()?.add(getLinderaService().userCMS(object : ResponseListener<Response<BaseResponse<CmsData>>, String> {
            override fun onSuccess(response: Response<BaseResponse<CmsData>>) {
                if (response.isSuccessful) {
                    response.body()?.run {
                        getSession()?.setAppCmsData(data)
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

    fun userAccountInfo() {
        getCompositeDisposable()?.add(getLinderaService().userAccountInfo(object : ResponseListener<Response<BaseResponse<JsonElement>>, String> {
            override fun onSuccess(response: Response<BaseResponse<JsonElement>>) {
                if (response.isSuccessful) {
                    response.body()?.run {
                        getSession().setAccountInfo(data)
                        getNavigator()?.onAccountInfoSuccess(data)
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

    fun fetchContactAndUpload() {
        val q = Contacts.getQuery()
        q.hasPhoneNumber()
        getCompositeDisposable()?.run {
            add(Observable.just(q.find()).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe {
                        Log.d("mytag", "contacts::" + it.size)
                    })
        }
    }
}