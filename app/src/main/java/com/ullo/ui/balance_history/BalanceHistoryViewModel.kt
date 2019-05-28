package com.ullo.ui.balance_history

import android.app.Application
import com.ullo.api.service.UlloService
import com.ullo.base.BaseViewModel
import com.ullo.db.DataManager
import com.ullo.utils.Session


class BalanceHistoryViewModel(application: Application, ulloService: UlloService, session: Session, dataManager: DataManager) : BaseViewModel<BalanceHistoryNavigator>(application, ulloService, session, dataManager) {

}