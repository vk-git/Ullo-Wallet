package com.ullo.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ullo.api.service.UlloService
import com.ullo.db.DataManager
import com.ullo.ui.change_password.ChangePasswordViewModel
import com.ullo.ui.choose_patient.ChoosePatientViewModel
import com.ullo.ui.contact.ContactViewModel
import com.ullo.ui.forgot_password.ForgotPasswordViewModel
import com.ullo.ui.landing.LandingViewModel
import com.ullo.ui.login.LoginViewModel
import com.ullo.ui.main.MainViewModel
import com.ullo.ui.privacy_policy.PrivacyPolicyViewModel
import com.ullo.ui.profile.ProfileViewModel
import com.ullo.ui.register.RegisterViewModel
import com.ullo.ui.setting.SettingViewModel
import com.ullo.ui.splash.SplashViewModel
import com.ullo.ui.terms_of_use.TermsOfUseViewModel
import com.ullo.ui.tutorial.TutorialViewModel
import io.reactivex.disposables.CompositeDisposable

class ViewModelProviderFactory : ViewModelProvider.Factory {

    private var mDataManager: DataManager

    private var mApplication: Application

    private var mUlloService: UlloService

    private var mSession: Session

    private var mCompositeDisposable: CompositeDisposable

    constructor(application: Application, ulloService: UlloService, session: Session, mDataManager: DataManager) {
        this.mApplication = application
        this.mUlloService = ulloService
        this.mSession = session
        this.mCompositeDisposable = CompositeDisposable()
        this.mDataManager = mDataManager
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ChangePasswordViewModel::class.java) -> ChangePasswordViewModel(mApplication, mUlloService, mSession, mDataManager) as T
            modelClass.isAssignableFrom(ChoosePatientViewModel::class.java) -> ChoosePatientViewModel(mApplication, mUlloService, mSession, mDataManager) as T
            modelClass.isAssignableFrom(ContactViewModel::class.java) -> ContactViewModel(mApplication, mUlloService, mSession, mDataManager) as T
            modelClass.isAssignableFrom(LandingViewModel::class.java) -> LandingViewModel(mApplication, mUlloService, mSession, mDataManager) as T
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(mApplication, mUlloService, mSession, mDataManager) as T
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(mApplication, mUlloService, mSession, mDataManager) as T
            modelClass.isAssignableFrom(PrivacyPolicyViewModel::class.java) -> PrivacyPolicyViewModel(mApplication, mUlloService, mSession, mDataManager) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel(mApplication, mUlloService, mSession, mDataManager) as T
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> RegisterViewModel(mApplication, mUlloService, mSession, mDataManager) as T
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> SplashViewModel(mApplication, mUlloService, mSession, mDataManager) as T
            modelClass.isAssignableFrom(TermsOfUseViewModel::class.java) -> TermsOfUseViewModel(mApplication, mUlloService, mSession, mDataManager) as T
            modelClass.isAssignableFrom(ForgotPasswordViewModel::class.java) -> ForgotPasswordViewModel(mApplication, mUlloService, mSession, mDataManager) as T
            modelClass.isAssignableFrom(TutorialViewModel::class.java) -> TutorialViewModel(mApplication, mUlloService, mSession, mDataManager) as T
            modelClass.isAssignableFrom(SettingViewModel::class.java) -> SettingViewModel(mApplication, mUlloService, mSession, mDataManager) as T
            else -> throw IllegalArgumentException("Unknown class name")
        }
    }
}