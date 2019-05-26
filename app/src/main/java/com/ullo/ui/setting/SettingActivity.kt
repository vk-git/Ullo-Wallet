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
import com.ullo.ui.contact.ContactActivity
import com.ullo.ui.landing.LandingActivity
import com.ullo.ui.my_qr_code.MyQrCodeActivity
import com.ullo.ui.profile.ProfileActivity
import com.ullo.ui.webview.WebviewActivity
import com.ullo.utils.Constant
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
        val intent = MyQrCodeActivity.newIntent(this)
        startActivity(intent)
    }

    override fun onAddCardHandle() {

    }

    override fun onChangePasswordHandle() {
        val intent = ChangePasswordActivity.newIntent(this)
        startActivity(intent)
    }

    override fun onTermsConditionHandle() {
        viewModel.getSession().getAppCmsData()?.run {
            val intent = WebviewActivity.newIntent(this@SettingActivity).apply {
                putExtra(Constant.PARAM_WEBVIEW_TITLE, "Terms & Conditions")
                putExtra(Constant.PARAM_WEBVIEW_URL, termsAndCondition)
            }
            startActivity(intent)
        }
    }

    override fun onPrivacyPolicesHandle() {
        viewModel.getSession().getAppCmsData()?.run {
            val intent = WebviewActivity.newIntent(this@SettingActivity).apply {
                putExtra(Constant.PARAM_WEBVIEW_TITLE, "Privacy Policy")
                putExtra(Constant.PARAM_WEBVIEW_URL, privacy)
            }
            startActivity(intent)
        }
    }

    override fun onContactUsHandle() {
        val intent = ContactActivity.newIntent(this)
        startActivity(intent)
    }

    override fun onAboutUsHandle() {
        viewModel.getSession().getAppCmsData()?.run {
            val intent = WebviewActivity.newIntent(this@SettingActivity).apply {
                putExtra(Constant.PARAM_WEBVIEW_TITLE, "About Us")
                putExtra(Constant.PARAM_WEBVIEW_URL, aboutUs)
            }
            startActivity(intent)
        }
    }

    override fun onSignOutHandle() {
        val intent = LandingActivity.newIntent(this)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }
}