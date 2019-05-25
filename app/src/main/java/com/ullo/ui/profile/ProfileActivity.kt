package com.ullo.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.ullo.BR
import com.ullo.R
import com.ullo.base.BaseActivity
import com.ullo.databinding.ActivityProfileBinding
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
}