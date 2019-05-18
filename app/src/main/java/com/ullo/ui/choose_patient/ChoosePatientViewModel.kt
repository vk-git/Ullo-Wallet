package com.ullo.ui.choose_patient

import android.app.Application
import com.ullo.api.service.UlloService
import com.ullo.base.BaseViewModel
import com.ullo.db.DataManager
import com.ullo.utils.Session


class ChoosePatientViewModel(application: Application, ulloService: UlloService, session: Session, dataManager: DataManager) : BaseViewModel<ChoosePatientNavigator>(application,ulloService, session, dataManager) {

}