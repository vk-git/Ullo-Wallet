package com.ullo.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.google.gson.JsonObject
import com.ullo.BR
import com.ullo.BuildConfig
import com.ullo.R
import com.ullo.base.BaseActivity
import com.ullo.databinding.ActivityLoginBinding
import com.ullo.ui.forgot_password.ForgotPasswordActivity
import com.ullo.ui.main.MainActivity
import com.ullo.ui.register.RegisterActivity
import com.ullo.utils.Validation
import com.ullo.utils.ViewModelProviderFactory
import javax.inject.Inject

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), LoginNavigator {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    @set:Inject
    lateinit var factory: ViewModelProviderFactory

    override val viewModel: LoginViewModel
        get() = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)

    private var mActivityLoginBinding: ActivityLoginBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val isFullScreen: Boolean
        get() = false

    override val layoutId: Int
        get() = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityLoginBinding = getViewDataBinding()
        viewModel.setNavigator(this)
    }

    override fun onLoginHandle() {
        if (isValid()) {
            val loginReq = JsonObject()
            loginReq.addProperty("email", mActivityLoginBinding!!.txtUserName.text.toString())
            loginReq.addProperty("password", mActivityLoginBinding!!.txtUserPassword.text.toString())
            loginReq.addProperty("user_type", BuildConfig.USER_TYPE)
            loginReq.addProperty("device_id", viewModel.getSession().getAppDeviceId())
            loginReq.addProperty("device_type", BuildConfig.DEVICE_TYPE)
            viewModel?.login(loginReq)
        }
    }

    private fun isValid(): Boolean {
        var bEmail = true
        var bPassword = true
        val email = mActivityLoginBinding!!.txtUserName.text.toString()
        val password = mActivityLoginBinding!!.txtUserPassword.text.toString()

        if (!Validation.isValidEmail(email)) {
            mActivityLoginBinding!!.tIEmail.isErrorEnabled = true
            mActivityLoginBinding!!.tIEmail.error = "The entered Email is not correct."
            bEmail = false
        } else {
            mActivityLoginBinding!!.tIEmail.isErrorEnabled = false
        }

        if (!Validation.isValidPassword(password)) {
            mActivityLoginBinding!!.tIPassword.isErrorEnabled = true
            mActivityLoginBinding!!.tIPassword.error = "Password must be at least 8 characters"
            bPassword = false
        } else {
            mActivityLoginBinding!!.tIPassword.isErrorEnabled = false
        }

        return bEmail && bPassword
    }

    override fun onForgotPasswordHandle() {
        val intent = ForgotPasswordActivity.newIntent(this)
        startActivity(intent)
    }

    override fun onMainScreen() {
        viewModel.let {
            val intent = MainActivity.newIntent(this)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }

    override fun onSignUpHandle() {
        val intent = RegisterActivity.newIntent(this)
        startActivity(intent)
    }
}