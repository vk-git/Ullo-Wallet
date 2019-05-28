package com.ullo.ui.choose_contact

import android.app.Application
import android.util.Log
import com.github.tamir7.contacts.Contact
import com.github.tamir7.contacts.Contacts
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.ullo.App
import com.ullo.R
import com.ullo.api.ResponseListener
import com.ullo.api.response.BaseResponse
import com.ullo.api.response.contact.ContactData
import com.ullo.api.service.UlloService
import com.ullo.base.BaseViewModel
import com.ullo.db.DataManager
import com.ullo.utils.Session
import com.ullo.utils.SharedPreferenceHelper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response


class ChooseContactViewModel(application: Application, ulloService: UlloService, session: Session, dataManager: DataManager) : BaseViewModel<ChooseContactNavigator>(application, ulloService, session, dataManager) {

    fun fetchContactAndUpload() {
        val q = Contacts.getQuery()
        q.hasPhoneNumber()
        getCompositeDisposable()?.run {
            add(Observable.just(q.find()).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe {
                        Log.d("mytag", "contacts::" + it.size)
                        uploadContact(it)
                    })
        }
    }

    private fun uploadContact(contactList: List<Contact>) {
        var phoneNumber = ""
        for (i in 0..contactList.size) {
            val contact = contactList[i]
            for (i1 in 0..contact.phoneNumbers.size) {
                phoneNumber += "," + contact.phoneNumbers[i1]
            }
        }
        val contactReq = JsonObject()
        contactReq.addProperty("phone_numbers", phoneNumber)
        Log.d("mytag", "Json:$contactReq")
    }

    fun userContactlist(registerReq: JsonObject) {
        App.instance.showLoadingOverlayDialog(App.instance.getString(R.string.loading))
        getCompositeDisposable()?.add(getLinderaService().userContactlist(registerReq, object : ResponseListener<Response<BaseResponse<ContactData>>, String> {
            override fun onSuccess(response: Response<BaseResponse<ContactData>>) {
                App.instance.hideLoadingOverlayDialog()
                if (response.isSuccessful) {
                    getNavigator()?.onContactSuccessfully()
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