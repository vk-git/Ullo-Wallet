package com.ullo.ui.notification

import android.app.Application
import com.ullo.api.service.UlloService
import com.ullo.base.BaseViewModel
import com.ullo.db.DataManager
import com.ullo.utils.Session


class NotificationViewModel(application: Application, ulloService: UlloService, session: Session, dataManager: DataManager) : BaseViewModel<NotificationNavigator>(application, ulloService, session, dataManager) {

}