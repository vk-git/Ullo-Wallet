package com.ullo.ui.main

import android.app.Application
import com.ullo.api.response.patient.Patient
import com.ullo.api.service.UlloService
import com.ullo.base.BaseViewModel
import com.ullo.db.DataManager
import com.ullo.utils.Session

class MainViewModel(application: Application, ulloService: UlloService, session: Session, dataManager: DataManager) : BaseViewModel<MainNavigator>(application, ulloService, session, dataManager) {

    fun definePatientLists(list: List<Patient>) {
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
}