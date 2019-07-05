package com.ullo.ui.manage_money

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.google.gson.JsonObject
import com.ullo.BR
import com.ullo.R
import com.ullo.base.BaseActivity
import com.ullo.databinding.ActivityManageMoneyBinding
import com.ullo.extensions.disable
import com.ullo.extensions.visible
import com.ullo.ui.setting.SettingActivity
import com.ullo.utils.Validation
import com.ullo.utils.ViewModelProviderFactory
import javax.inject.Inject

class ManageMoneyActivity : BaseActivity<ActivityManageMoneyBinding, ManageMoneyViewModel>(), ManageMoneyNavigator {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ManageMoneyActivity::class.java)
        }
    }

    @set:Inject
    lateinit var factory: ViewModelProviderFactory

    override val viewModel: ManageMoneyViewModel
        get() = ViewModelProviders.of(this, factory).get(ManageMoneyViewModel::class.java)

    private var mActivityManageMoneyBinding: ActivityManageMoneyBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val isFullScreen: Boolean
        get() = false

    override val layoutId: Int
        get() = R.layout.activity_manage_money

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityManageMoneyBinding = getViewDataBinding()
        viewModel.setNavigator(this)

        mActivityManageMoneyBinding!!.toolbar.setBackButton(true)
        mActivityManageMoneyBinding!!.toolbar.setBackButtonListener(listener = View.OnClickListener {
            finish()
        })

        mActivityManageMoneyBinding!!.toolbar.setRightButtonListener(listener = View.OnClickListener {
            SettingActivity.newIntent(this).apply {
                startActivity(this)
            }
        })

        mActivityManageMoneyBinding!!.etAmount.requestFocus()
        mActivityManageMoneyBinding!!.btnUpdate.setOnClickListener {
            if (mActivityManageMoneyBinding!!.btnUpdate.text.toString() == "Add Money") {
                if (isValid()) {
                    val req = JsonObject()
                    req.addProperty("amount", mActivityManageMoneyBinding!!.etAmount.text.toString())
                    viewModel.userAddMoney(req)
                }
            } else {
                finish()
            }
        }
    }

    private fun isValid(): Boolean {
        var bAmount = true
        val amount = mActivityManageMoneyBinding!!.etAmount.text.toString()

        if (!Validation.isValidNumber(amount)) {
            //  mActivitySendMoneyBinding!!.etAmount.isErrorEnabled = true
            mActivityManageMoneyBinding!!.etAmount.error = "The entered amount is not correct."
            bAmount = false
        } else {
            //  mActivitySendMoneyBinding!!.etAmount.isErrorEnabled = false
        }

        return bAmount
    }

    override fun onAddMoneySuccess() {
        mActivityManageMoneyBinding!!.imgSuccess.visible()
        mActivityManageMoneyBinding!!.btnUpdate.text = "Close"
        mActivityManageMoneyBinding!!.toolbar.setToolbarTitle("Success")
        mActivityManageMoneyBinding!!.txtContactInfo.text = "Payment Successfully added to your wallet"
        mActivityManageMoneyBinding!!.etAmount.disable()
    }
}