package com.ullo.ui.splash

import android.app.Application
import android.os.Handler
import com.ullo.api.service.UlloService
import com.ullo.base.BaseViewModel
import com.ullo.db.DataManager
import com.ullo.utils.Session


class SplashViewModel(application: Application, ulloService: UlloService, session: Session, dataManager: DataManager) : BaseViewModel<SplashNavigator>(application, ulloService, session, dataManager) {

    fun onTimeHandler() {
        Handler().postDelayed({
            if (getSession().getAppUserToken().isEmpty()) {
                getNavigator()!!.onLandingScreen()
            } else {
                getNavigator()!!.onMainScreen()
            }
        }, 3000)
    }
}