package com.ullo.base

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ullo.api.response.contact.Contact
import com.ullo.api.service.UlloService
import com.ullo.db.DataManager
import com.ullo.utils.Session
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference


abstract class BaseViewModel<N> : AndroidViewModel {

    private val mDataManager: DataManager

    private lateinit var mNavigator: WeakReference<N>

    private var mUlloService: UlloService

    private var mSession: Session

    private var mCompositeDisposable: CompositeDisposable

    private val mIsLoading = ObservableBoolean(false)
    private val mIsEmptyView = ObservableBoolean(false)

    val allPatients: LiveData<List<Contact>>
        get() =  getDataManager().allPatients

    constructor(application: Application, ulloService: UlloService, session: Session, mDataManager: DataManager) : super(application) {
        this.mUlloService = ulloService
        this.mSession = session
        this.mCompositeDisposable = CompositeDisposable()
        this.mDataManager = mDataManager
    }

    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }

    fun getCompositeDisposable(): CompositeDisposable? {
        return mCompositeDisposable
    }

    fun getNavigator(): N? {
        return mNavigator.get()
    }

    fun setNavigator(navigator: N) {
        this.mNavigator = WeakReference(navigator)
    }

    fun getIsLoading(): ObservableBoolean {
        return mIsLoading
    }

    fun setIsLoading(isLoading: Boolean) {
        mIsLoading.set(isLoading)
    }

    fun getIsEmptyView(): ObservableBoolean {
        return mIsEmptyView
    }

    fun setIsEmptyView(isEmptyView: Boolean) {
        mIsEmptyView.set(isEmptyView)
    }

    fun getLinderaService(): UlloService {
        return mUlloService
    }

    fun getSession(): Session {
        return mSession
    }

    fun getDataManager(): DataManager {
        return mDataManager
    }
}