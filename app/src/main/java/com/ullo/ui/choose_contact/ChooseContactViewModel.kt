package com.ullo.ui.choose_contact

import android.app.Application
import com.ullo.api.service.UlloService
import com.ullo.base.BaseViewModel
import com.ullo.db.DataManager
import com.ullo.utils.Session


class ChooseContactViewModel(application: Application, ulloService: UlloService, session: Session, dataManager: DataManager) : BaseViewModel<ChooseContactNavigator>(application,ulloService, session, dataManager) {

}