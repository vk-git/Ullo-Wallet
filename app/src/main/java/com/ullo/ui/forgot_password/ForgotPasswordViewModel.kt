package com.ullo.ui.forgot_password

import android.app.Application
import com.ullo.api.service.UlloService
import com.ullo.base.BaseViewModel
import com.ullo.db.DataManager
import com.ullo.utils.Session


class ForgotPasswordViewModel(application: Application, ulloService: UlloService, session: Session, dataManager: DataManager) : BaseViewModel<ForgotPasswordNavigator>(application, ulloService, session, dataManager) {

}