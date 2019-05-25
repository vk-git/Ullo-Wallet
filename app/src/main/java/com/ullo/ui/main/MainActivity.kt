package com.ullo.ui.main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.ullo.BR
import com.ullo.R
import com.ullo.base.BaseActivity
import com.ullo.databinding.ActivityMainBinding
import com.ullo.ui.choose_contact.ChooseContactActivity
import com.ullo.ui.setting.SettingActivity
import com.ullo.utils.ViewModelProviderFactory
import javax.inject.Inject


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {


    companion object {

        private const val READ_CONTACT_PERMISSION_REQUEST_CODE = 76

        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    @set:Inject
    lateinit var factory: ViewModelProviderFactory

    override val viewModel: MainViewModel
        get() = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

    private var mActivityMainBinding: ActivityMainBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val isFullScreen: Boolean
        get() = false

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityMainBinding = getViewDataBinding()
        viewModel.setNavigator(this)
        viewModel.getCms()

        viewModel.getSession().getAppUser()?.run {
            mActivityMainBinding!!.txtUserEmail.text = userData.email
            mActivityMainBinding!!.txtUserFullName.text = userData.fullName
        }

        fetchContacts()
    }

    private fun fetchContacts() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) ==
                PackageManager.PERMISSION_GRANTED) {
            viewModel.fetchContactAndUpload()
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS),
                        READ_CONTACT_PERMISSION_REQUEST_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == READ_CONTACT_PERMISSION_REQUEST_CODE
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            fetchContacts()
        }
    }

    override fun onMenuHandle() {
        val intent = SettingActivity.newIntent(this)
        startActivity(intent)
    }

    override fun onSendMoneyHandle() {
        val intent = ChooseContactActivity.newIntent(this)
        startActivity(intent)
    }
}
