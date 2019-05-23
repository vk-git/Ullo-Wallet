package com.ullo.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.ullo.BR
import com.ullo.R
import com.ullo.base.BaseActivity
import com.ullo.databinding.ActivitySettingBinding
import com.ullo.ui.change_password.ChangePasswordActivity
import com.ullo.ui.landing.LandingActivity
import com.ullo.ui.privacy_policy.PrivacyPolicyActivity
import com.ullo.ui.profile.ProfileActivity
import com.ullo.ui.terms_of_use.TermsOfUseActivity
import com.ullo.utils.ViewModelProviderFactory
import javax.inject.Inject

class SettingActivity : BaseActivity<ActivitySettingBinding, SettingViewModel>(), SettingNavigator {


    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SettingActivity::class.java)
        }
    }

    @set:Inject
    lateinit var factory: ViewModelProviderFactory

    override val viewModel: SettingViewModel
        get() = ViewModelProviders.of(this, factory).get(SettingViewModel::class.java)

    private var mActivitySettingBinding: ActivitySettingBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val isFullScreen: Boolean
        get() = false

    override val layoutId: Int
        get() = R.layout.activity_setting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivitySettingBinding = getViewDataBinding()
        viewModel.setNavigator(this)

        mActivitySettingBinding!!.toolbar.setBackButton(true)
        mActivitySettingBinding!!.toolbar.setBackButtonListener(listener = View.OnClickListener {
            finish()
        })

    }

    override fun onMyProfileHandle() {
        val intent = ProfileActivity.newIntent(this)
        startActivity(intent)
    }

    override fun onMyQRCodeHandle() {

    }

    override fun onAddCardHandle() {

    }

    override fun onChangePasswordHandle() {
        val intent = ChangePasswordActivity.newIntent(this)
        startActivity(intent)
    }

    override fun onTermsConditionHandle() {
        val intent = TermsOfUseActivity.newIntent(this)
        startActivity(intent)
    }

    override fun onPrivacyPolicesHandle() {
        val intent = PrivacyPolicyActivity.newIntent(this)
        startActivity(intent)
    }

    override fun onSignOutHandle() {
        val intent = LandingActivity.newIntent(this)
        startActivity(intent)
        finish()
    }
}