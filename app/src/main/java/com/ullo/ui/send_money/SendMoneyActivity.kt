package com.ullo.ui.send_money

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.ullo.BR
import com.ullo.R
import com.ullo.base.BaseActivity
import com.ullo.databinding.ActivitySendMoneyBinding
import com.ullo.utils.ViewModelProviderFactory
import javax.inject.Inject

class SendMoneyActivity : BaseActivity<ActivitySendMoneyBinding, SendMoneyViewModel>(), SendMoneyNavigator {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SendMoneyActivity::class.java)
        }
    }

    @set:Inject
    lateinit var factory: ViewModelProviderFactory

    override val viewModel: SendMoneyViewModel
        get() = ViewModelProviders.of(this, factory).get(SendMoneyViewModel::class.java)

    private var mActivitySendMoneyBinding: ActivitySendMoneyBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val isFullScreen: Boolean
        get() = false

    override val layoutId: Int
        get() = R.layout.activity_send_money

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivitySendMoneyBinding = getViewDataBinding()
        viewModel.setNavigator(this)

        mActivitySendMoneyBinding!!.toolbar.setBackButton(true)
        mActivitySendMoneyBinding!!.toolbar.setBackButtonListener(listener = View.OnClickListener {
            finish()
        })

    }
}