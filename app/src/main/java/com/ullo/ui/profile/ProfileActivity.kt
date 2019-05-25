package com.ullo.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.google.gson.JsonObject
import com.ullo.BR
import com.ullo.BuildConfig
import com.ullo.R
import com.ullo.api.response.AppUser
import com.ullo.base.BaseActivity
import com.ullo.databinding.ActivityProfileBinding
import com.ullo.utils.Validation
import com.ullo.utils.ViewModelProviderFactory
import javax.inject.Inject

class ProfileActivity : BaseActivity<ActivityProfileBinding, ProfileViewModel>(), ProfileNavigator {


    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ProfileActivity::class.java)
        }
    }

    @set:Inject
    lateinit var factory: ViewModelProviderFactory

    override val viewModel: ProfileViewModel
        get() = ViewModelProviders.of(this, factory).get(ProfileViewModel::class.java)

    private var mActivityProfileBinding: ActivityProfileBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val isFullScreen: Boolean
        get() = false

    override val layoutId: Int
        get() = R.layout.activity_profile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityProfileBinding = getViewDataBinding()
        viewModel.setNavigator(this)

        mActivityProfileBinding!!.toolbar.setBackButton(true)
        mActivityProfileBinding!!.toolbar.setBackButtonListener(listener = View.OnClickListener {
            finish()
        })
    }

    override fun onUpdateHandle() {
        if (isValid()) {
            val loginReq = JsonObject()
            loginReq.addProperty("full_name", mActivityProfileBinding!!.etFullName.text.toString())
            loginReq.addProperty("email", mActivityProfileBinding!!.etEmail.text.toString())
            loginReq.addProperty("country_code", mActivityProfileBinding!!.etCountry.text.toString())
            loginReq.addProperty("phone_number", mActivityProfileBinding!!.etMobileNo.text.toString())
            loginReq.addProperty("user_type", BuildConfig.USER_TYPE)
            loginReq.addProperty("device_id", viewModel.getSession().getAppDeviceId())
            loginReq.addProperty("device_type", BuildConfig.DEVICE_TYPE)
            viewModel.userUpdateProfile(loginReq)
        }
    }

    private fun isValid(): Boolean {
        var bFullName = true
        var bEmail = true
        var bCountry = true
        var bMobile = true
        val firstName = mActivityProfileBinding!!.etFullName.text.toString()
        val email = mActivityProfileBinding!!.etEmail.text.toString()
        val country = mActivityProfileBinding!!.etCountry.text.toString()
        val mobileNo = mActivityProfileBinding!!.etMobileNo.text.toString()

        if (!Validation.isValidName(firstName)) {
            mActivityProfileBinding!!.tIFullName.isErrorEnabled = true
            mActivityProfileBinding!!.tIFullName.error = "The entered Email is not correct."
            bFullName = false
        } else {
            mActivityProfileBinding!!.tIFullName.isErrorEnabled = false
        }

        if (!Validation.isValidEmail(email)) {
            mActivityProfileBinding!!.tIEmail.isErrorEnabled = true
            mActivityProfileBinding!!.tIEmail.error = "The entered Email is not correct."
            bEmail = false
        } else {
            mActivityProfileBinding!!.tIEmail.isErrorEnabled = false
        }

        if (!Validation.isValidName(country)) {
            mActivityProfileBinding!!.tICountry.isErrorEnabled = true
            mActivityProfileBinding!!.tICountry.error = "Password must be at least 8 characters"
            bCountry = false
        } else {
            mActivityProfileBinding!!.tICountry.isErrorEnabled = false
        }

        if (!Validation.isNumberInput(mobileNo)) {
            mActivityProfileBinding!!.tIMobileNo.isErrorEnabled = true
            mActivityProfileBinding!!.tIMobileNo.error = "Mobile number must be at least 10 characters"
            bMobile = false
        } else {
            mActivityProfileBinding!!.tIMobileNo.isErrorEnabled = false
        }

        return bFullName && bEmail && bCountry && bMobile
    }

    override fun onUpdateSuccessful(data: AppUser) {
        onShowAlert("Alert", "Profile Successfully Updated")
    }
}