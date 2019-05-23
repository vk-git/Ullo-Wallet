package com.ullo.ui.tutorial

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.linderaredux.adapter.CustomPagerAdapter
import com.ullo.BR
import com.ullo.R
import com.ullo.base.BaseActivity
import com.ullo.databinding.ActivityTutorialBinding
import com.ullo.ui.landing.LandingActivity
import com.ullo.utils.ViewModelProviderFactory
import javax.inject.Inject

class TutorialActivity : BaseActivity<ActivityTutorialBinding, TutorialViewModel>(), TutorialNavigator {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, TutorialActivity::class.java)
        }
    }

    @set:Inject
    lateinit var factory: ViewModelProviderFactory

    @set:Inject
    var mCustomPagerAdapter: CustomPagerAdapter? = null

    override val viewModel: TutorialViewModel
        get() = ViewModelProviders.of(this, factory).get(TutorialViewModel::class.java)

    private var mActivityTutorialBinding: ActivityTutorialBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val isFullScreen: Boolean
        get() = false

    override val layoutId: Int
        get() = R.layout.activity_tutorial

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityTutorialBinding = getViewDataBinding()
        viewModel.setNavigator(this)

        mActivityTutorialBinding!!.viewpager.apply {
            adapter = mCustomPagerAdapter
        }

        mActivityTutorialBinding!!.tabLayout.apply {
            setupWithViewPager(mActivityTutorialBinding!!.viewpager, true)
        }
    }

    override fun onNextHandle() {
        val intent = LandingActivity.newIntent(this)
        startActivity(intent)
        finish()
    }
}