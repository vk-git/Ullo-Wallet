package com.ullo.ui.main

import android.app.Application
import android.util.Log
import com.github.tamir7.contacts.Contacts
import com.ullo.api.response.contact.Contact
import com.ullo.api.service.UlloService
import com.ullo.base.BaseViewModel
import com.ullo.db.DataManager
import com.ullo.utils.Session
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainViewModel(application: Application, ulloService: UlloService, session: Session, dataManager: DataManager) : BaseViewModel<MainNavigator>(application, ulloService, session, dataManager) {


    fun onMenuButtonClick(){
        getNavigator()?.onMenuHandle()
    }

    fun onSendMoneyButtonClick(){
        getNavigator()?.onSendMoneyHandle()
    }

    fun definePatientLists(list: List<Contact>) {
        /* getCompositeDisposable()?.run {
             add(getDataManager().savePatientList(list)
                     .subscribeOn(Schedulers.io())
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe {
                         if (it) {
                             Log.d("mytatg", "Successfully added")
                         }
                     })
         }*/
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