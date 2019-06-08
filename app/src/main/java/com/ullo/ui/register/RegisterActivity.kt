package com.ullo.ui.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.lifecycle.ViewModelProviders
import com.google.gson.JsonObject
import com.ullo.BR
import com.ullo.BuildConfig
import com.ullo.R
import com.ullo.api.response.AppUser
import com.ullo.base.BaseActivity
import com.ullo.databinding.ActivityRegisterBinding
import com.ullo.extensions.gone
import com.ullo.extensions.visible
import com.ullo.ui.login.LoginActivity
import com.ullo.ui.main.MainActivity
import com.ullo.utils.Validation
import com.ullo.utils.ViewModelProviderFactory
import javax.inject.Inject

class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel>(), RegisterNavigator {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    @set:Inject
    var factory: ViewModelProviderFactory? = null

    override val viewModel: RegisterViewModel
        get() = ViewModelProviders.of(this, factory).get(RegisterViewModel::class.java)

    private var mActivityRegisterBinding: ActivityRegisterBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val isFullScreen: Boolean
        get() = false

    override val layoutId: Int
        get() = R.layout.activity_register

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityRegisterBinding = getViewDataBinding()
        viewModel.setNavigator(this)

        val deviceId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID);
        viewModel.getSession().setAppDeviceId(deviceId)
    }

    override fun onLoginScreen() {
        LoginActivity.newIntent(this).apply {
            startActivity(this)
        }
    }

    override fun onCheckValidation() {
        if (isValid()) {
            val loginReq = JsonObject()
            loginReq.addProperty("full_name", mActivityRegisterBinding!!.etFullName.text.toString())
            loginReq.addProperty("email", mActivityRegisterBinding!!.etEmail.text.toString())
            loginReq.addProperty("password", mActivityRegisterBinding!!.etPassword.text.toString())
            loginReq.addProperty("country_code", mActivityRegisterBinding!!.etCountryCode.text.toString())
            loginReq.addProperty("phone_number", mActivityRegisterBinding!!.etMobileNo.text.toString())
            loginReq.addProperty("user_type", BuildConfig.USER_TYPE)
            loginReq.addProperty("device_id", viewModel.getSession().getAppDeviceId())
            loginReq.addProperty("device_type", BuildConfig.DEVICE_TYPE)
            viewModel.register(loginReq)
        }
    }

    override fun onRegisterSuccessful(appUser: AppUser) {
       MainActivity.newIntent(this).apply {
           startActivity(this)
       }
    }

    private fun isValid(): Boolean {
        var bFullName = true
        var bEmail = true
        var bPassword = true
        var bCountryCode = true
        val firstName = mActivityRegisterBinding!!.etFullName.text.toString()
        val email = mActivityRegisterBinding!!.etEmail.text.toString()
        val password = mActivityRegisterBinding!!.etPassword.text.toString()
        val mobileNo = mActivityRegisterBinding!!.etMobileNo.text.toString()
        val countryCode = mActivityRegisterBinding!!.etCountryCode.text.toString()

        if (!Validation.isValidName(firstName)) {
            mActivityRegisterBinding!!.tIFullName.isErrorEnabled = true
            mActivityRegisterBinding!!.tIFullName.error = "The entered name is not correct."
            bFullName = false
        } else {
            mActivityRegisterBinding!!.tIFullName.isErrorEnabled = false
        }

        if (!Validation.isValidEmail(email)) {
            mActivityRegisterBinding!!.tIEmail.isErrorEnabled = true
            mActivityRegisterBinding!!.tIEmail.error = "The entered Email is not correct."
            bEmail = false
        } else {
            mActivityRegisterBinding!!.tIEmail.isErrorEnabled = false
        }

        if (!Validation.isValidPassword(password)) {
            mActivityRegisterBinding!!.tIPassword.isErrorEnabled = true
            mActivityRegisterBinding!!.tIPassword.error = "Password must be at least 8 characters"
            bPassword = false
        } else {
            mActivityRegisterBinding!!.tIPassword.isErrorEnabled = false
        }

        if (mobileNo.isEmpty() || !Validation.isValidMobile(mobileNo)) {
            mActivityRegisterBinding!!.tIMobileNo.visible()
            mActivityRegisterBinding!!.tIMobileNo.text = "Not Valid Number"
            bCountryCode = false
        } else {
            mActivityRegisterBinding!!.tIMobileNo.gone()
        }

        if (countryCode.isEmpty() || mobileNo.isEmpty() || !Validation.isValidCountryCode(countryCode, mobileNo)) {
            mActivityRegisterBinding!!.tIMobileNo.visible()
            mActivityRegisterBinding!!.tIMobileNo.text = "Not Valid Country Code"
            bCountryCode = false
        } else {
            mActivityRegisterBinding!!.tIMobileNo.gone()
        }

        return bFullName && bEmail && bPassword && bCountryCode
    }
}