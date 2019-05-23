package com.ullo.ui.choose_patient

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.tamir7.contacts.Contact
import com.github.tamir7.contacts.Contacts
import com.ullo.BR
import com.ullo.R
import com.ullo.adapter.ContactAdapter
import com.ullo.base.BaseActivity
import com.ullo.databinding.ActivityChoosePatientBinding
import com.ullo.utils.ViewModelProviderFactory
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ChoosePatientActivity : BaseActivity<ActivityChoosePatientBinding, ChoosePatientViewModel>(), ChoosePatientNavigator {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ChoosePatientActivity::class.java)
        }
    }

    @set:Inject
    lateinit var factory: ViewModelProviderFactory

    override val viewModel: ChoosePatientViewModel
        get() = ViewModelProviders.of(this, factory).get(ChoosePatientViewModel::class.java)

    @set:Inject
    var contactAdapter: ContactAdapter? = null

    private var mActivityChoosePatientBinding: ActivityChoosePatientBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val isFullScreen: Boolean
        get() = false

    override val layoutId: Int
        get() = R.layout.activity_choose_patient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityChoosePatientBinding = getViewDataBinding()
        viewModel?.setNavigator(this)

        mActivityChoosePatientBinding!!.toolbar.setBackButton(true)
        mActivityChoosePatientBinding!!.toolbar.setBackButtonListener(listener = View.OnClickListener {
            finish()
        })

        viewModel.run {
            val q = Contacts.getQuery()
            q.hasPhoneNumber()
            getCompositeDisposable()?.run {
                add(Observable.just(q.find()).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe {
                            Log.d("mytag", "Contact::" + it.size)
                            setContactData(it)
                        })
            }
            // getDataManager().allPatients.observeForever { patientList -> setPatientData(patientList) }
        }
    }

    private fun setContactData(patientList: List<Contact>) {
        with(mActivityChoosePatientBinding!!.recyclerView) {
            layoutManager = LinearLayoutManager(this@ChoosePatientActivity)
            adapter = contactAdapter
        }

        contactAdapter?.run { setContactListData(patientList as ArrayList<Contact>) }
    }
}