package com.ullo.ui.privacy_policy

import android.app.Application
import com.ullo.api.service.UlloService
import com.ullo.base.BaseViewModel
import com.ullo.db.DataManager
import com.ullo.utils.Session


class PrivacyPolicyViewModel(application: Application, ulloService: UlloService, session: Session, dataManager: DataManager) : BaseViewModel<PrivacyPolicyNavigator>(application, ulloService, session, dataManager) {

}