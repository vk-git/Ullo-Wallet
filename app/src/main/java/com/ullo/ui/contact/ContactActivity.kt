package com.ullo.ui.contact

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.ullo.BR
import com.ullo.R
import com.ullo.base.BaseActivity
import com.ullo.databinding.ActivityContactBinding
import com.ullo.utils.ViewModelProviderFactory
import javax.inject.Inject

class ContactActivity : BaseActivity<ActivityContactBinding, ContactViewModel>(), ContactNavigator {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ContactActivity::class.java)
        }
    }

   @set:Inject
    lateinit var factory: ViewModelProviderFactory

    override val viewModel: ContactViewModel
        get() = ViewModelProviders.of(this, factory).get(ContactViewModel::class.java)

    private var mActivityContactBinding: ActivityContactBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val isFullScreen: Boolean
        get() = false

    override val layoutId: Int
        get() = R.layout.activity_contact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityContactBinding = getViewDataBinding()
        viewModel.setNavigator(this)

        mActivityContactBinding!!.toolbar.setBackButton(true)
        mActivityContactBinding!!.toolbar.setBackButtonListener(listener = View.OnClickListener {
            finish()
        })
    }
}