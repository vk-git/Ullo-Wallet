package com.ullo.ui.terms_of_use

import android.app.Application
import com.ullo.api.service.UlloService
import com.ullo.base.BaseViewModel
import com.ullo.db.DataManager
import com.ullo.utils.Session


class TermsOfUseViewModel(application: Application, ulloService: UlloService, session: Session, dataManager: DataManager) : BaseViewModel<TermsOfUseNavigator>(application, ulloService, session, dataManager) {

}