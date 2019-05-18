package com.ullo.ui.landing

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.ullo.BR
import com.ullo.R
import com.ullo.base.BaseActivity
import com.ullo.databinding.ActivityLandingBinding
import com.ullo.ui.login.LoginActivity
import com.ullo.ui.register.RegisterActivity
import com.ullo.utils.ViewModelProviderFactory
import javax.inject.Inject

class LandingActivity : BaseActivity<ActivityLandingBinding, LandingViewModel>(), LandingNavigator {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LandingActivity::class.java)
        }
    }

    @set:Inject
    lateinit var factory: ViewModelProviderFactory

    override val viewModel: LandingViewModel
        get() = ViewModelProviders.of(this, factory).get(LandingViewModel::class.java)

    private var mActivityLandingBinding: ActivityLandingBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val isFullScreen: Boolean
        get() = false

    override val layoutId: Int
        get() = R.layout.activity_landing

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityLandingBinding = getViewDataBinding()
        viewModel?.setNavigator(this)
    }

    override fun onLoginScreen() {
        val intent = LoginActivity.newIntent(this)
        startActivity(intent)
    }

    override fun onRegisterScreen() {
        val intent = RegisterActivity.newIntent(this)
        startActivity(intent)
    }
}