package com.ullo.dagger

import com.ullo.ui.change_password.ChangePasswordActivity
import com.ullo.ui.choose_patient.ChoosePatientActivity
import com.ullo.ui.choose_patient.ChoosePatientActivityModule
import com.ullo.ui.contact.ContactActivity
import com.ullo.ui.forgot_password.ForgotPasswordActivity
import com.ullo.ui.landing.LandingActivity
import com.ullo.ui.login.LoginActivity
import com.ullo.ui.main.MainActivity
import com.ullo.ui.privacy_policy.PrivacyPolicyActivity
import com.ullo.ui.profile.ProfileActivity
import com.ullo.ui.register.RegisterActivity
import com.ullo.ui.setting.SettingActivity
import com.ullo.ui.setting.SettingViewModel
import com.ullo.ui.splash.SplashActivity
import com.ullo.ui.terms_of_use.TermsOfUseActivity
import com.ullo.ui.tutorial.TutorialActivity
import com.ullo.ui.tutorial.TutorialActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    internal abstract fun bindLandingActivity(): LandingActivity

    @ContributesAndroidInjector
    internal abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    internal abstract fun bindRegisterActivity(): RegisterActivity

    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun bindContactActivity(): ContactActivity

    @ContributesAndroidInjector
    internal abstract fun bindProfileActivity(): ProfileActivity

    @ContributesAndroidInjector
    internal abstract fun bindPrivacyPolicyActivity(): PrivacyPolicyActivity

    @ContributesAndroidInjector
    internal abstract fun bindTermsOfUseActivity(): TermsOfUseActivity

    @ContributesAndroidInjector
    internal abstract fun bindChangePasswordActivity(): ChangePasswordActivity

    @ContributesAndroidInjector(modules = [ChoosePatientActivityModule::class])
    internal abstract fun bindChoosePatientActivity(): ChoosePatientActivity

    @ContributesAndroidInjector
    internal abstract fun bindForgotPasswordActivity(): ForgotPasswordActivity

    @ContributesAndroidInjector(modules = [TutorialActivityModule::class])
    internal abstract fun bindTutorialActivity(): TutorialActivity

    @ContributesAndroidInjector
    internal abstract fun bindSettingActivity(): SettingActivity
}