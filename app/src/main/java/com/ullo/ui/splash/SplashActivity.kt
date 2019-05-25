package com.ullo.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.lifecycle.ViewModelProviders
import com.ullo.BR
import com.ullo.R
import com.ullo.base.BaseActivity
import com.ullo.databinding.ActivitySplashBinding
import com.ullo.ui.landing.LandingActivity
import com.ullo.ui.main.MainActivity
import com.ullo.ui.tutorial.TutorialActivity
import com.ullo.utils.ViewModelProviderFactory
import javax.inject.Inject

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), SplashNavigator {


    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SplashActivity::class.java)
        }
    }

    @set:Inject
    lateinit var factory: ViewModelProviderFactory

    override val viewModel: SplashViewModel
        get() = ViewModelProviders.of(this, factory).get(SplashViewModel::class.java)

    private var mActivitySplashBinding: ActivitySplashBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val isFullScreen: Boolean
        get() = true

    override val layoutId: Int
        get() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivitySplashBinding = getViewDataBinding()
        viewModel.setNavigator(this)

        viewModel.onTimeHandler()
    }

    override fun onLandingScreen() {
        val intent = LandingActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

    override fun onMainScreen() {
        val intent = MainActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

    override fun onTutorialScreen() {
        val intent = TutorialActivity.newIntent(this)
        startActivity(intent)
        finish()
    }
}