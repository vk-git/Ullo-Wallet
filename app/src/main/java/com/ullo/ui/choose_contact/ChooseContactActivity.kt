package com.ullo.ui.choose_contact

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.tamir7.contacts.Contact
import com.github.tamir7.contacts.Contacts
import com.ullo.BR
import com.ullo.R
import com.ullo.adapter.ContactAdapter
import com.ullo.base.BaseActivity
import com.ullo.databinding.ActivityChooseContactBinding
import com.ullo.utils.ViewModelProviderFactory
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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

        viewModel.run {
            val q = Contacts.getQuery()
            q.hasPhoneNumber()
            getCompositeDisposable()?.run {
                add(Observable.just(q.find()).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe {
                            setContactData(it)
                        })
            }
            // getDataManager().allPatients.observeForever { patientList -> setPatientData(patientList) }
        }
    }

    private fun setContactData(patientList: List<Contact>) {
        with(mActivityChooseContactBinding!!.recyclerView) {
            layoutManager = LinearLayoutManager(this@ChooseContactActivity)
            adapter = contactAdapter
        }

        contactAdapter?.run { setContactListData(patientList as ArrayList<Contact>) }
    }
}