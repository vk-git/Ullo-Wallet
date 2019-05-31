package com.ullo.ui.setting

import android.app.Application
import com.ullo.api.service.UlloService
import com.ullo.base.BaseViewModel
import com.ullo.db.DataManager
import com.ullo.utils.Session
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SettingViewModel(application: Application, ulloService: UlloService, session: Session, dataManager: DataManager) : BaseViewModel<SettingNavigator>(application, ulloService, session, dataManager) {

    fun onMyProfileButtonClick() {
        getNavigator()?.onMyProfileHandle()
    }

    fun onMyQRCodeButtonClick() {
        getNavigator()?.onMyQRCodeHandle()
    }

    fun onAddCardButtonClick() {
        getNavigator()?.onAddCardHandle()
    }

    fun onChangePasswordButtonClick() {
        getNavigator()?.onChangePasswordHandle()
    }

    fun onTermsConditionButtonClick() {
        getNavigator()?.onTermsConditionHandle()
    }

    fun onPrivacyPolicesButtonClick() {
        getNavigator()?.onPrivacyPolicesHandle()
    }

    fun onContactUsButtonClick() {
        getNavigator()?.onContactUsHandle()
    }

    fun onAboutUsButtonClick() {
        getNavigator()?.onAboutUsHandle()
    }

    fun onSignOutButtonClick() {
        getCompositeDisposable()?.add(getDataManager().deleteAllTables()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it) {
                        getSession().logout()
                        getNavigator()?.onSignOutHandle()
                    }
                })
    }
}