package com.ullo.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.ullo.BR
import com.ullo.R
import com.ullo.base.BaseActivity
import com.ullo.databinding.ActivityLoginBinding
import com.ullo.ui.forgot_password.ForgotPasswordActivity
import com.ullo.ui.main.MainActivity
import com.ullo.utils.Validation
import com.ullo.utils.Validation.ValidationType
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

        mActivityLoginBinding!!.txtUserName.addTextChangedListener(Validation(mActivityLoginBinding!!.txtUserName, ValidationType.Email))
        mActivityLoginBinding!!.txtUserPassword.addTextChangedListener(Validation(mActivityLoginBinding!!.txtUserPassword, ValidationType.Password))
    }

    override fun onLoginHandle() {
        onMainScreen()
        /*if (isValid()) {
            val loginReq = JsonObject()
            loginReq.addProperty("email", mActivityLoginBinding!!.txtUserName.text.toString())
            loginReq.addProperty("password", mActivityLoginBinding!!.txtUserPassword.text.toString())
            viewModel?.login(loginReq)
        }*/
    }

    private fun isValid(): Boolean {
        var validInput = true
        val email = mActivityLoginBinding!!.txtUserName.text.toString()
        val password = mActivityLoginBinding!!.txtUserPassword.text.toString()

        if (!Validation.isValidEmail(email)) {
            validInput = false
            mActivityLoginBinding!!.tIEmail.isErrorEnabled = true
            mActivityLoginBinding!!.tIEmail.error = "The entered Email is not correct."
        } else {
            mActivityLoginBinding!!.tIEmail.isErrorEnabled = false
        }

        if (!Validation.isValidPassword(password)) {
            validInput = false
            mActivityLoginBinding!!.tIPassword.isErrorEnabled = true
            mActivityLoginBinding!!.tIPassword.error = "Password must be at least 8 characters"
        } else {
            mActivityLoginBinding!!.tIPassword.isErrorEnabled = false
        }

        return validInput
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
}