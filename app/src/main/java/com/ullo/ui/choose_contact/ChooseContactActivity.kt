package com.ullo.ui.choose_contact

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ullo.BR
import com.ullo.R
import com.ullo.adapter.ContactAdapter
import com.ullo.api.response.contact.Contact
import com.ullo.base.BaseActivity
import com.ullo.databinding.ActivityChooseContactBinding
import com.ullo.ui.scan_qr_code.ScanQrCodeActivity
import com.ullo.ui.send_money.SendMoneyActivity
import com.ullo.utils.Constant
import com.ullo.utils.RxSearch
import com.ullo.utils.ViewModelProviderFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ChooseContactActivity : BaseActivity<ActivityChooseContactBinding, ChooseContactViewModel>(), ChooseContactNavigator {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ChooseContactActivity::class.java)
        }
    }

    @set:Inject
    lateinit var factory: ViewModelProviderFactory

    override val viewModel: ChooseContactViewModel
        get() = ViewModelProviders.of(this, factory).get(ChooseContactViewModel::class.java)

    @set:Inject
    var contactAdapter: ContactAdapter? = null

    private var mActivityChooseContactBinding: ActivityChooseContactBinding? = null

    lateinit var contactList: List<Contact>

    override val bindingVariable: Int
        get() = BR.viewModel

    override val isFullScreen: Boolean
        get() = false

    override val layoutId: Int
        get() = R.layout.activity_choose_contact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityChooseContactBinding = getViewDataBinding()
        viewModel.setNavigator(this)

        mActivityChooseContactBinding!!.toolbar.setBackButton(true)
        mActivityChooseContactBinding!!.toolbar.setBackButtonListener(listener = View.OnClickListener {
            finish()
        })

        viewModel.fetchContactAndUpload()
        viewModel.run {
            allContacts.observeForever {
                setContactData(it)
            }
        }

        with(mActivityChooseContactBinding!!.etSearch) {
            RxSearch.fromView(this)
                    .debounce(300, TimeUnit.MILLISECONDS)
                    .distinctUntilChanged()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { result ->
                        if (result.isNotEmpty()) {
                            viewModel.filter(contactList, result)
                        } else {
                            setContactData(contactList)
                        }
                    }
        }
    }

    override fun onContactSuccessfully() {

    }

    private fun setContactData(contactList: List<Contact>) {
        this.contactList = contactList
        with(mActivityChooseContactBinding!!.recyclerView) {
            layoutManager = LinearLayoutManager(this@ChooseContactActivity)
            adapter = contactAdapter
        }

        contactAdapter?.run { setContactListData(contactList as ArrayList<Contact>) }

        contactAdapter?.setOnContactItemListener(object : ContactAdapter.OnContactItemListener {
            override fun onItemClick(contact: Contact) {
                SendMoneyActivity.newIntent(this@ChooseContactActivity).apply {
                    putExtra(Constant.PARAM_CONTACT, contact)
                    startActivity(this)
                }
            }
        })
    }

    override fun setFilterPatientList(filteredDataList: ArrayList<Contact>) {
        contactAdapter?.run {
            setContactListData(filteredDataList)
        }
    }

    override fun onScanQrCodeHandle() {
        ScanQrCodeActivity.newIntent(this).apply {
            startActivity(this)
        }
    }
}