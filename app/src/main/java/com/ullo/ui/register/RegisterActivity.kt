package com.ullo.ui.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.ullo.BR
import com.ullo.R
import com.ullo.api.response.AppUser
import com.ullo.base.BaseActivity
import com.ullo.databinding.ActivityRegisterBinding
import com.ullo.ui.login.LoginActivity
import com.ullo.ui.main.MainActivity
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
        viewModel?.setNavigator(this)

        /* mActivityRegisterBinding!!.etFirstName.addTextChangedListener(Validation(mActivityRegisterBinding!!.etFirstName, Validation.ValidationType.CompanyName))
         mActivityRegisterBinding!!.etLastName.addTextChangedListener(Validation(mActivityRegisterBinding!!.etLastName, Validation.ValidationType.CompanyName))
         mActivityRegisterBinding!!.etEmail.addTextChangedListener(Validation(mActivityRegisterBinding!!.etEmail, Validation.ValidationType.Email))
         mActivityRegisterBinding!!.etPassword.addTextChangedListener(Validation(mActivityRegisterBinding!!.etPassword, Validation.ValidationType.Password))
         mActivityRegisterBinding!!.etRepeatPassword.addTextChangedListener(Validation(mActivityRegisterBinding!!.etRepeatPassword, Validation.ValidationType.Password))*/
    }

    override fun onLoginScreen() {
        val intent = LoginActivity.newIntent(this)
        startActivity(intent)
    }

    override fun onCheckValidation() {
        if (isValid()) {
            /* val loginReq = JsonObject()
             loginReq.addProperty("firstName", mActivityRegisterBinding!!.etFirstName.text.toString())
             loginReq.addProperty("lastName", mActivityRegisterBinding!!.etLastName.text.toString())
             loginReq.addProperty("email", mActivityRegisterBinding!!.etEmail.text.toString())
             loginReq.addProperty("password", mActivityRegisterBinding!!.etPassword.text.toString())
             loginReq.addProperty("userrole", mActivityRegisterBinding!!.txtPosition.text.toString())
             loginReq.addProperty("userimage", "")
             loginReq.add("homeMemberShip", JsonObject())
             viewModel?.register(loginReq)*/
        }
    }

    override fun onRegisterSuccessful(appUser: AppUser) {
        val intent = MainActivity.newIntent(this)
        startActivity(intent)
    }

    private fun isValid(): Boolean {
        var validInput = true
        /*val firstName = mActivityRegisterBinding!!.etFirstName.text.toString()
        val lastName = mActivityRegisterBinding!!.etFirstName.text.toString()
        val email = mActivityRegisterBinding!!.etEmail.text.toString()
        val password = mActivityRegisterBinding!!.etPassword.text.toString()
        val repeatPassword = mActivityRegisterBinding!!.etRepeatPassword.text.toString()
        val teamAndCondition = mActivityRegisterBinding!!.cbTermAndCondition.isChecked()

        if (!Validation.isValidName(firstName)) {
            validInput = false
            mActivityRegisterBinding!!.tIFirstName.isErrorEnabled = true
            mActivityRegisterBinding!!.tIFirstName.error = "The entered Email is not correct."
        } else {
            mActivityRegisterBinding!!.tIFirstName.isErrorEnabled = false
        }

        if (!Validation.isValidName(lastName)) {
            validInput = false
            mActivityRegisterBinding!!.tILastName.isErrorEnabled = true
            mActivityRegisterBinding!!.tILastName.error = "The entered Email is not correct."
        } else {
            mActivityRegisterBinding!!.tILastName.isErrorEnabled = false
        }

        if (!Validation.isValidEmail(email)) {
            validInput = false
            mActivityRegisterBinding!!.tIEmail.isErrorEnabled = true
            mActivityRegisterBinding!!.tIEmail.error = "The entered Email is not correct."
        } else {
            mActivityRegisterBinding!!.tIEmail.isErrorEnabled = false
        }

        if (!Validation.isValidPassword(password)) {
            validInput = false
            mActivityRegisterBinding!!.tIPassword.isErrorEnabled = true
            mActivityRegisterBinding!!.tIPassword.error = "Password must be at least 8 characters"
        } else {
            mActivityRegisterBinding!!.tIPassword.isErrorEnabled = false
        }

        if (!Validation.isValidPassword(repeatPassword)) {
            validInput = false
            mActivityRegisterBinding!!.tIRepeatPassword.isErrorEnabled = true
            mActivityRegisterBinding!!.tIRepeatPassword.error = "Password must be at least 8 characters"
        } else {
            if (!Validation.isMatchPassword(password, repeatPassword)) {
                validInput = false
                mActivityRegisterBinding!!.tIRepeatPassword.isErrorEnabled = true
                mActivityRegisterBinding!!.tIRepeatPassword.error = "Password not match"
            } else {
                mActivityRegisterBinding!!.tIRepeatPassword.isErrorEnabled = false
            }
        }

        validInput = if (!teamAndCondition) {
            handleError("Please select team and condition")
            false
        } else {
            true
        }*/

        return validInput
    }
}