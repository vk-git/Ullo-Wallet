package com.ullo.ui.choose_patient

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.ullo.BR
import com.ullo.R
import com.ullo.adapter.ContactAdapter
import com.ullo.api.response.patient.Patient
import com.ullo.base.BaseActivity
import com.ullo.databinding.ActivityChoosePatientBinding
import com.ullo.utils.ViewModelProviderFactory
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

                        })
            }
           // getDataManager().allPatients.observeForever { patientList -> setPatientData(patientList) }
        }
    }

    private fun setPatientData(patientList: List<Patient>) {
        // val patientSectionList = Sort.onPatientListWithAlphabeticalSection(patientList)

        /*with(mActivityChoosePatientBinding!!.recyclerView) {
            layoutManager = StickyHeaderLayoutManager()
            adapter = mPatientAdapter
        }

        patientSections = patientSectionList
        patientSections?.let {
            mPatientAdapter?.setPatientSections(it)
        }

        mPatientAdapter!!.setOnPatientItemListener(object : PatientAdapter.OnPatientItemListener {
            override fun onItemClick(patient: Patient) {
                val intent = EditPatientActivity.newIntent(this@ChoosePatientActivity)
                intent.putExtra(Constant.PARAM_PATIENT, patient)
                startActivity(intent)
            }
        })

        mActivityChoosePatientBinding!!.alphabetView.onSectionIndexClickListener(object : Alphabetik.SectionIndexClickListener {
            override fun onItemClick(view: View, position: Int, character: String) {
                val scrollIndex = getPositionFromData(character)
                if (scrollIndex != -1) {
                    val scrollToPosition = mPatientAdapter?.getAdapterPositionForSectionHeader(scrollIndex)
                    if (scrollToPosition != -1)
                        mActivityChoosePatientBinding!!.recyclerView.scrollToPosition(scrollToPosition!!)
                }
            }
        })*/
    }

    /*private fun getPositionFromData(character: String): Int {
        for ((position, patientSection) in patientSections.withIndex()) {
            val letter = patientSection.headerTitle
            if (letter == character) {
                return position
            }
        }
        return -1
    }*/
}